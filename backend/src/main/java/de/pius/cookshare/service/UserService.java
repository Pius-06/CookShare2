package de.pius.cookshare.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.DTO.userDTO.UserRequestDTO;
import de.pius.cookshare.exception.custom_exception.conflict.UserAlreadyExistsException;
import de.pius.cookshare.exception.custom_exception.not_found.UserNotFoundException;
import de.pius.cookshare.mapper.UserMapper;
import de.pius.cookshare.model.User;
import de.pius.cookshare.repository.UserRepository;
import jakarta.transaction.Transactional;

public class UserService {

    private UserRepository userRepository;

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

    public User createUser(UserRequestDTO userData) {
        User user = UserMapper.toUser(userData);

        checkUsernameAvailable(user.getUsername());
        checkEmailAvailable(user.getEmail());

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long userId, UserRequestDTO userData) {
        checkIdlAvailable(userId);
        
        return null;
    }

    public User deleteUser(Long id) {
        return null;
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
