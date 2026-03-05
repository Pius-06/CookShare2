package de.pius.cookshare.token.refresh_token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.pius.cookshare.user.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String refreshToken);

    @Query("""
            SELECT t
            FROM RefreshToken t
            WHERE t.user.id = ?1
            """)
    List<RefreshToken> findAllValidTokensByUser(Long userId);

    List<RefreshToken> findByUser(User user);

    @Modifying
    @Transactional
    @Query("""
            DELETE FROM RefreshToken t
            WHERE t.user = :user
            """)
    void deleteByUser(@Param("user") User user);

    @Modifying
    @Transactional
    @Query("""
            DELETE FROM RefreshToken t
            WHERE t.token = :tokenname
            """)
    void deleteByTokenname(@Param("tokenname") String tokenname);
}
