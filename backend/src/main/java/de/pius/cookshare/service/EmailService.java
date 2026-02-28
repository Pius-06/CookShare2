package de.pius.cookshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username:no-reply@cookshare.de}")
    private String fromMail;

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
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

    public void sendVerificationEmail(String email, String token) {
        String link = "https://cookshare.de/auth/verify?token=" + token; // TODO: Domain anpassen
        String subject = "Registrierung bestätigen bei CookShare";
        
        String text = String.format("""
            Bitte bestätige deine Registrierung bei CookShare:
            
            Klicke auf folgenden Link, um deine Email zu verifizieren:
            %s
            """, link);
        
        sendMail(email, subject, text);
    }
}