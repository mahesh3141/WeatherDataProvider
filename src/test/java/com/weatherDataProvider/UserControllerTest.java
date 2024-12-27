package com.weatherDataProvider;

import com.weatherDataProvider.controller.UserController;
import com.weatherDataProvider.entity.User;
import com.weatherDataProvider.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @InjectMocks
    UserController controller;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setActive(false);

        when(userService.createUser("New User")).thenReturn(user);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .param("name","Test User")
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test User"))
                .andExpect(jsonPath("$.active").value(false));

    }

    @Test
    public void testActivateUser() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setActive(true);

        when(userService.deactivateUser(1L)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/6/activate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    public void testDeactivateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setActive(false);

        when(userService.deactivateUser(1L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/6/deactivate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(false));
    }
}
