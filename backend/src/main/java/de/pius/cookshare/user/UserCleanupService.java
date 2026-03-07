package de.pius.cookshare.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCleanupService {
    private UserRepository userRepository;

    public UserCleanupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public int deleteExpiredUsers() {
        List<User> users = userRepository.findByExpiredToken();
        users.forEach((user) -> userRepository.delete(user)); 
        return users.size();
    }
}
