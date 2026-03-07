package de.pius.cookshare.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager; // Hilfsklasse zum Speichern/Flushen

    @Test
    void itShouldReturnUserWithExpiredUnusedToken() {
        User user = UserDataFactory.userWithToken(
                "Chef_06",
                "lisa@example.com",
                false,
                LocalDateTime.now().minusDays(1));
        entityManager.persistAndFlush(user);

        List<User> result = userRepository.findAllExpiredUnusedToken();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("lisa@example.com");
    }

    @Test
    void itShouldNotReturnUserWithUsedToken() {
        User user = UserDataFactory.userWithToken(
                "Pan_Hero",
                "tim@example.com",
                true,
                LocalDateTime.now().minusDays(1));
        entityManager.persistAndFlush(user);

        assertThat(userRepository.findAllExpiredUnusedToken()).isEmpty();
    }

    @Test
    void itShouldNotReturnUserWithActiveToken() {
        User user = UserDataFactory.userWithToken(
                "Max3000",
                "max@example.com",
                false,
                LocalDateTime.now().plusDays(1));
        entityManager.persistAndFlush(user);

        assertThat(userRepository.findAllExpiredUnusedToken()).isEmpty();
    }
}
