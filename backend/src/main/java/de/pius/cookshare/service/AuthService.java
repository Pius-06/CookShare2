package de.pius.cookshare.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.pius.cookshare.dto.auth.RegisterRequestDTO;
import de.pius.cookshare.exception.custom_exception.bad_request.VerificationTokenAlreadyUsed;
import de.pius.cookshare.exception.custom_exception.gone.VerificationTokenExpiredException;
import de.pius.cookshare.exception.custom_exception.not_found.VerificationTokenNotFoundException;
import de.pius.cookshare.model.user.User;
import de.pius.cookshare.model.user.VerificationToken;
import de.pius.cookshare.repository.VerificationTokenRepository;

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
