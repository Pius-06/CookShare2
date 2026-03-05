package de.pius.cookshare.token.email_verification_token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

    @Query("""
            SELECT t
            FROM EmailVerificationToken t
            WHERE t.token = ?1
            """)
    Optional<EmailVerificationToken> findByToken(String token);
}