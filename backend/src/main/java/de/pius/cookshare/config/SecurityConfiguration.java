package de.pius.cookshare.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import de.pius.cookshare.auth.LogoutService;
import de.pius.cookshare.security.JwtAuthenticationFilter;

@Configuration // Spring-Konfigurationsklasse
@EnableWebSecurity // aktiviert Spring Security
@RequiredArgsConstructor // erzeugt Konstruktor für final Felder
@EnableMethodSecurity // erlaubt @PreAuthorize, @Secured usw. auf Methoden
public class SecurityConfiguration {

        private static final String[] WHITE_LIST_URL = {
                        "/auth/**"
        };

        // liest JWT aus dem Header und authentifiziert den User
        private final JwtAuthenticationFilter jwtAuthFilter;
        // prüft Benutzer + Passwort / Token
        private final AuthenticationProvider authenticationProvider;
        // löscht Token / Session beim Logout
        private final LogoutService logoutService;

        /*
         * SecurityConfig wird nur beim Start des servers ausgeführt!!!!!!!!!!!
         * 1. Request kommt bei Spring → Dispatcher.
         * 2. Spring Security Filter Chain läuft vor deinem Controller.
         * 3. SecurityFilterChain:
         * - Prüft CSRF, CORS, Session
         * - Prüft Authentifizierung via jwtAuthFilter etc.
         * - Prüft Authorization (requestMatchers + .hasRole() / .hasAuthority())
         * - Erlaubt Weiterleitung zum Controller oder
         * - wirft AccessDeniedException / AuthenticationException
         * 4. Controller wird nur erreicht, wenn alles passt.
         * => SecurityFilterChain ist wie Torwächter:
         * - entscheidet, wer rein darf, blockiert alles andere
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(req -> this.configureAuthorization(req))
                                // Keine HTTP Session
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                // Spring Security sagt: „Wenn ich einen User authentifizieren muss, benutze
                                // dieses AuthenticationProvider-Objekt.“
                                .authenticationProvider(authenticationProvider)
                                // JWT wird vor Username/Password geprüft
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                // User ausloggen
                                .logout(logout -> logout.logoutUrl("/auth/logout")
                                                // Token wird ungültig gemacht
                                                .addLogoutHandler(logoutService)
                                                .logoutSuccessHandler((req, res, auth) -> SecurityContextHolder
                                                                .clearContext()));

                return http.build();
        }

        private AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configureAuthorization(
                        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                return registry
                                // Öffentlich
                                .anyRequest().permitAll();

                                // Für alle anderen Anfragen, die nicht durch die vorherigen Regeln abgedeckt
                                // wurden
                                //.anyRequest()
                                // Verlange, dass der Benutzer authentifiziert ist
                                //.authenticated();
        }

}