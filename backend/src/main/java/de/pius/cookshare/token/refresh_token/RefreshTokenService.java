package de.pius.cookshare.token.refresh_token;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.pius.cookshare.token.AbstractTokenService;
import de.pius.cookshare.user.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RefreshTokenService extends AbstractTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final long REFRESH_EXPIRATION = 604_800_000; // Tage

    public String generateRefreshToken(User user) {
        String token = buildToken(new HashMap<>(), user, REFRESH_EXPIRATION);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .revoked(false)
                .expiresAt(LocalDateTime.now().plusSeconds(REFRESH_EXPIRATION)) 
                .build();

        refreshTokenRepository.save(refreshToken);
        return token;
    }

    /*
     * Bei neuem Login werden alle Refreshtoken ungültig gemacht, sodass
     * der User einen neuen bekommen kann und diesen nutzen kann
     */
    public void deleteUserRefreshTokens(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
    
    public void deleteRefreshTokenByName(String tokenname) {
        refreshTokenRepository.deleteByTokenname(tokenname);
    }

    /*
    public void revokeRefreshToken(String refreshToken) {
        RefreshToken validToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenNotFound("refresh token", refreshToken));

        validToken.setRevoked(true);
        validToken.setExpiresAt(LocalDateTime.now().minusSeconds(1));
        refreshTokenRepository.save(validToken);
    }*/
}
