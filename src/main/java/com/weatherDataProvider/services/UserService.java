package com.weatherDataProvider.services;

import com.weatherDataProvider.entity.User;
import com.weatherDataProvider.exceptionHandler.CustomExceptionHandler;
import com.weatherDataProvider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name) {
        User user = new User();
        user.setName(name);
        user.setActive(false); // Default to inactive
        return userRepository.save(user);
    }

    public User activateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptionHandler("User not found"));
        user.setActive(true);
        return userRepository.save(user);
    }

    public User deactivateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptionHandler("User not found"));
        user.setActive(false);
        return userRepository.save(user);
    }

    public boolean checkUserStatus(String username) {
        User user = userRepository.findByName(username);
        return user.isActive();
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public List<User> getAllUserByStatus(boolean flag) {
        List<User> list = userRepository.findAllUserByActive(flag);
        return list;
    }
}
