package de.pius.cookshare.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("""
            SELECT u 
            FROM User u
            WHERE u.isActive = false
            AND u.emailVerificationToken.used = false
            AND u.emailVerificationToken.expiresAt < CURRENT_TIMESTAMP
            """)
    List<User> findAllExpiredUnusedToken();
}
