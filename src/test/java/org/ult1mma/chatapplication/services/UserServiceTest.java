package org.ult1mma.chatapplication.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.ult1mma.chatapplication.entities.User;
import org.ult1mma.chatapplication.repositories.UserRepository;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("password");
        testUser.setEmail("test@test.com");
        testUser.setCreatedAt(LocalDateTime.now());
        testUser.setActive(true);

    }

    @Test
    void testRegisterUser_Success() {

    }

}
