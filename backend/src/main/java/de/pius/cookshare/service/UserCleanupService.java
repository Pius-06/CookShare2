package de.pius.cookshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.pius.cookshare.model.user.User;
import de.pius.cookshare.repository.UserRepository;

@Service
public class UserCleanupService {
    private UserRepository userRepository;

    @Autowired
    public UserCleanupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public int deleteExpiredUsers() {
        List<User> users = userRepository.findUsersByExpiredToken();
        users.forEach((user) -> userRepository.delete(user)); 
        return users.size();
    }
}
