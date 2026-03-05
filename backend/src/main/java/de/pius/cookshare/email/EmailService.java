package de.pius.cookshare.email;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.pius.cookshare.token.email_verification_token.EmailVerificationToken;
import de.pius.cookshare.token.email_verification_token.EmailVerificationTokenRepository;
import de.pius.cookshare.token.email_verification_token.exception.EmailVerificationTokenNotFoundException;
import de.pius.cookshare.user.User;
import de.pius.cookshare.user.UserService;

@Service
public class EmailService {

    @Value("${spring.mail.username:no-reply@cookshare.de}")
    private String fromMail;

    private final JavaMailSender mailSender;
    private final EmailVerificationTokenRepository tokenRepository;
    private final UserService userService;

    public EmailService(JavaMailSender mailSender,
            EmailVerificationTokenRepository tokenRepository,
            UserService userService) {
        this.mailSender = mailSender;
        this.tokenRepository = tokenRepository;
        this.userService = userService;
    }

    public void createAndSendToken(User user) {
        EmailVerificationToken emailVerificationToken = createToken(user);
        tokenRepository.save(emailVerificationToken);

        sendVerificationEmail(user.getEmail(), emailVerificationToken.getToken());
    }

    private EmailVerificationToken createToken(User user) {
        String token = UUID.randomUUID().toString();

        return EmailVerificationToken.builder()
                .token(token)
                .user(user)
                .expiresAt(LocalDateTime.now().plusDays(1))
                .build();
    }

    @Transactional
    public void verifyEmail(String token) {
        EmailVerificationToken verificationToken = tokenRepository
                .findByToken(token)
                .orElseThrow(() -> new EmailVerificationTokenNotFoundException("token", token));

        verificationToken.validate();

        userService.activateAccount(verificationToken.getUser().getId());
        tokenRepository.delete(verificationToken);
    }

    public void sendMail(String email, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom(fromMail);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email konnte nicht gesendet werden", e);
        }
    }

    private void sendVerificationEmail(String email, String token) {
        String link = "http://localhost:8080/auth/verify?token=" + token; 
        String subject = "Registrierung bestätigen bei CookShare";

        String text = String.format("""
                Bitte bestätige deine Registrierung bei CookShare:

                Klicke auf folgenden Link, um deine Email zu verifizieren:
                %s
                """, link);

        sendMail(email, subject, text);
    }

}