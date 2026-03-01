package de.pius.cookshare.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.pius.cookshare.auth.dto.RegisterRequestDTO;
import de.pius.cookshare.email.EmailService;
import de.pius.cookshare.token.email_verification_token.VerificationToken;
import de.pius.cookshare.token.email_verification_token.VerificationTokenRepository;
import de.pius.cookshare.token.email_verification_token.exception.VerificationTokenAlreadyUsed;
import de.pius.cookshare.token.email_verification_token.exception.VerificationTokenExpiredException;
import de.pius.cookshare.token.email_verification_token.exception.VerificationTokenNotFoundException;
import de.pius.cookshare.user.User;
import de.pius.cookshare.user.UserService;

@Service
public class AuthService {

    private final UserService userService;
    private final EmailService emailService;
    private final VerificationTokenRepository tokenRepository;

    @Autowired
    public AuthService(
            UserService userService,
            EmailService emailService,
            VerificationTokenRepository tokenRepository) {
        this.userService = userService;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }

    // TODO: Wann Transactional auch hier?
    public void login(String email, String password) {
        
    }

    @Transactional // ???
    public void register(RegisterRequestDTO dto) {
        User user = userService.createUser(dto);

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expiresAt(LocalDateTime.now().plusDays(1))
                .build();

        tokenRepository.save(verificationToken);

        emailService.sendVerificationEmail(user.getEmail(), token);
    }

    public void verifyEmail(String token) {
        VerificationToken verificationToken = tokenRepository
                .findByToken(token)
                .orElseThrow(() -> new VerificationTokenNotFoundException("token", token));

        if(verificationToken.isExpired()) throw new VerificationTokenExpiredException();
        if(verificationToken.isUsed()) throw new VerificationTokenAlreadyUsed();

        verificationToken.markAsUsed();
        Long userId = verificationToken.getUser().getId();
        userService.activateAccount(userId);
        tokenRepository.delete(verificationToken);
    }
}
