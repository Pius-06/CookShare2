package de.pius.cookshare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.pius.cookshare.model.user.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    @Query("""
            SELECT v
            FROM VerificationToken v
            WHERE v.token = ?1
            """)
    Optional<VerificationToken> findByToken(String token);
}