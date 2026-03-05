package de.pius.cookshare.security;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import de.pius.cookshare.token.jwt_token.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@Component // Spring erstellt automatisch eine Instanz dieses Filters.
@RequiredArgsConstructor
// OncePerRequestFilter: Filter läuft genau einmal pro Request (ideal für JWT).
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private static final String[] WHITELIST = {
            "/auth/"
    };

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return Arrays.stream(WHITELIST)
                .anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Wenn kein JWT gesendet wird => Filter überspringen
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String email = jwtService.extractEmail(jwt);

        /*
         * 2. Wenn der Benutzer schon authentifiziert ist (z. B. in einem vorherigen
         * Filter, oder beim Request erneut), braucht man nicht noch einmal zu
         * authentifizieren.
         */
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            authenticateUserFromJwt(request, jwt, email);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUserFromJwt(
            HttpServletRequest request,
            String jwt,
            String email) {
        // Lädt den Benutzer aus DB
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

        if (jwtService.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, // Wer ist eingelogt
                    null, // Passwort (nicht benötigt da JWT)
                    userDetails.getAuthorities()); // Was darf der User

            // Fügt technische Infos hinzu: IP-Adresse, Session-ID und Browser-Info
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Ab diesem Moment weiß Spring: "Dieser Request gehört zu User X"
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

}
