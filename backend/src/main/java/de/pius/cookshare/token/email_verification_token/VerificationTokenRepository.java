package de.pius.cookshare.token.email_verification_token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    @Query("""
            SELECT v
            FROM VerificationToken v
            WHERE v.token = ?1
            """)
    Optional<VerificationToken> findByToken(String token);
}