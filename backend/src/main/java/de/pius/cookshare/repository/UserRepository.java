package de.pius.cookshare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.pius.cookshare.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
            SELECT u
            FROM User u
            WHERE u.email = ?1
            """)
    Optional<User> findUserByEmail(String email);

    @Query("""
            SELECT u
            FROM User u
            WHERE u.username = ?1
            """)
    Optional<User> findUserByUsername(String username);

    @Query("""
            SELECT
                CASE
                    WHEN COUNT(u) > 0 THEN true
                    ELSE false
                END
            FROM User u
            WHERE u.email = ?1
            """)
    boolean existsByEmail(String email);

    @Query("""
            SELECT
                CASE
                    WHEN COUNT(u) > 0 THEN true
                    ELSE false
                END
            FROM User u
            WHERE u.username = ?1
            """)
    boolean existsByUsername(String username);

    @Query("""
            SELECT
                CASE
                    WHEN COUNT(u) > 0 THEN true
                    ELSE false
                END
            FROM User u
            WHERE u.id = ?1
            """)
    boolean existsById(Long id);

    @Query("""
                SELECT u
                FROM User u
                WHERE u.isActive = false
                AND u.verificationToken.used = false
                AND u.verificationToken.expiresAt < CURRENT_TIMESTAMP
            """)
    List<User> findUsersByExpiredToken();
}
