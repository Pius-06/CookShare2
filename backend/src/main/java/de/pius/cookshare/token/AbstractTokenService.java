package de.pius.cookshare.token;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public abstract class AbstractTokenService {

    @Value("${app.jwtSecret}")
    protected String jwtSecretKey;

    protected Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    protected String buildToken(Map<String, Object> claims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    protected Claims extractAllClaims(String jwt) {
        return Jwts
                // erzeugt ein Objekt, dass Jwt lesen, prüfen und extrahiern kann
                .parserBuilder()
                // Mit dem Secret Key soll überprüft werden, ob das Token echt ist
                .setSigningKey(getSignInKey())
                // Baut den fertigen Parser
                .build()
                // Prüft JWT
                .parseClaimsJws(jwt)
                // Holt den Payload-Teil = Claims
                .getBody();
    }

    protected Date extractExpiration(String token) {
        return (Date) extractAllClaims(token).getExpiration();
    }
}