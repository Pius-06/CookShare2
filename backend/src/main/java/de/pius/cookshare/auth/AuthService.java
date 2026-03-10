package de.pius.cookshare.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.pius.cookshare.auth.dto.AuthRequest;
import de.pius.cookshare.auth.dto.AuthResponse;
import de.pius.cookshare.auth.dto.RegisterRequestDTO;
import de.pius.cookshare.token.jwt_token.JwtService;
import de.pius.cookshare.email.EmailService;
import de.pius.cookshare.token.refresh_token.RefreshTokenService;
import de.pius.cookshare.user.User;
import de.pius.cookshare.user.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;

    private final EmailService emailService;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;
    
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()));

        User user = (User) authentication.getPrincipal();

        refreshTokenService.deleteUserRefreshTokens(user);

        String jwt = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.generateRefreshToken(user);

        return new AuthResponse(jwt, refreshToken);
    }

    @Transactional 
    public void register(RegisterRequestDTO dto) {
        User user = userService.createUser(dto);
        emailService.createAndSendToken(user);
    }

    public void verifyEmail(String token) {
        emailService.verifyEmail(token);
    }

    
}
