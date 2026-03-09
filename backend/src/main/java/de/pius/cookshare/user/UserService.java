package de.pius.cookshare.user;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.pius.cookshare.auth.AuthMapper;
import de.pius.cookshare.auth.dto.RegisterRequestDTO;
import de.pius.cookshare.user.dto.PasswordUpdateDTO;
import de.pius.cookshare.user.dto.UserUpdateDTO;
import de.pius.cookshare.user.exception.UserAlreadyExistsException;
import de.pius.cookshare.user.exception.UserNotFoundException;
import de.pius.cookshare.user.exception.password.OldPasswordMismatchException;
import de.pius.cookshare.user.exception.password.PasswordSameAsOldException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Set<User> getAllUser() {
        return userRepository.findAll()
                .stream()
                .collect(Collectors.toSet());
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id.toString()));
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email.toString()));
    }

    @Transactional
    public User createUser(RegisterRequestDTO dto) {
        User user = AuthMapper.toUser(dto);

        checkUsernameAvailable(user.getUsername());
        checkEmailAvailable(user.getEmail());

        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, UserUpdateDTO userData) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id.toString()));

        if (userData.username() != null && !userData.username().equals(user.getUsername())) {
            checkUsernameAvailable(userData.username());
        }

        if (userData.email() != null && !userData.email().equals(user.getEmail())) {
            checkEmailAvailable(userData.email());
        }

        return UserMapper.updateUser(userData, user);
    }

    @Transactional
    public void updatePassword(Long id, PasswordUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id.toString()));

        if (!passwordEncoder.matches(dto.oldPassword(), user.getPassword())) {
            throw new OldPasswordMismatchException();
        }

        if (passwordEncoder.matches(dto.newPassword(), user.getPassword())) {
            throw new PasswordSameAsOldException();
        }
        user.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("id", id.toString());
        }

        userRepository.deleteById(id);
    }

    @Transactional
    public void activateAccount(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id.toString()));

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
}
