package com.weatherDataProvider;

import com.weatherDataProvider.entity.User;
import com.weatherDataProvider.repository.UserRepository;
import com.weatherDataProvider.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() {
        User newUser = userService.createUser("New User");
        assertNotNull(newUser.getId());
        assertEquals("New User", newUser.getName());
        assertFalse(newUser.isActive());
    }

    @Test
    public void testActivateUser() {
        User user = new User();
        user.setName("Test User");
        user.setActive(false);

        User savedUser = userRepository.save(user);

        User activatedUser = userService.activateUser(savedUser.getId());
        assertTrue(activatedUser.isActive());
    }
    @Test
    public void testDeactivateUser() {
        User user = new User();
        user.setName("Test User");
        user.setActive(true);

        User savedUser = userRepository.save(user);

        User deactivatedUser = userService.deactivateUser(savedUser.getId());
        assertFalse(deactivatedUser.isActive());
    }
}
