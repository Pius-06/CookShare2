package de.pius.cookshare.user;

import java.time.LocalDateTime;

import de.pius.cookshare.token.email_verification_token.EmailVerificationToken;

public class UserDataFactory {

    public static int tokencount = 0;

    public static User validUser(String username, String email) {
        return User.builder()
                .username(username)
                .firstname("First")
                .lastname("Last")
                .email(email)
                .password("password")
                .isActive(false)
                .role(Role.USER)
                .build();
    }

    public static User userWithToken(
            String username,
            String email,
            boolean used,
            LocalDateTime expiresAt) {

        User user = validUser(username, email);

        EmailVerificationToken token = EmailVerificationToken.builder()
                .token("token" + ++tokencount)
                .used(used)
                .expiresAt(expiresAt)
                .user(user)
                .build();

        user.setEmailVerificationToken(token);
        return user;
    }
}