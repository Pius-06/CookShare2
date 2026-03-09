package de.pius.cookshare.token.jwt_token;

import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import de.pius.cookshare.token.AbstractTokenService;


@Service
public class JwtService extends AbstractTokenService {

    private final long EXPIRATION = 1_800_000*10; // 30min // TODO: *10 nur zu Testzwecken

    public String generateToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, EXPIRATION);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractAllClaims(token).getSubject().equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }
}
