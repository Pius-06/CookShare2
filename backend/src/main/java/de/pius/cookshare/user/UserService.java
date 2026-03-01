package de.pius.cookshare.user;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.pius.cookshare.auth.AuthMapper;
import de.pius.cookshare.auth.dto.RegisterRequestDTO;
import de.pius.cookshare.user.dto.UserRequestDTO;
import de.pius.cookshare.user.exception.UserAlreadyExistsException;
import de.pius.cookshare.user.exception.UserNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> getAllUser() {
        return userRepository.findAll()
                .stream()
                .collect(Collectors.toSet());
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id.toString()));
    }

    public User createUser(RegisterRequestDTO userData) {
        User user = AuthMapper.toUser(userData);

        checkUsernameAvailable(user.getUsername());
        checkEmailAvailable(user.getEmail());

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long userId, UserRequestDTO userData) {
        checkIdlAvailable(userId);
        // kein passwort ändern lassen können
        return null;
    }

    public User deleteUser(Long id) {
        return null;
    }

    @Transactional
    public void activateAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("id", userId.toString()));

        user.setActive(true);
    }

    private void checkUsernameAvailable(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("username", username);
        }
    }

    private void checkEmailAvailable(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("email", email);
        }
    }

    private void checkIdlAvailable(Long id) {
        if (userRepository.existsById(id)) {
            throw new UserAlreadyExistsException("id", id.toString());
        }
    }
}
