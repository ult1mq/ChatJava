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
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

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
        testUser.setPassword("encodedPassword");
        testUser.setEmail("test@test.com");
        testUser.setCreatedAt(LocalDateTime.now());
        testUser.setActive(true);

    }

    @Test
    void testRegisterUser_Success() {
        Mockito.when(userRepository.findByUsername("test")).thenReturn(null);
        Mockito.when(userRepository.findByEmail("test@test.com")).thenReturn(null);
        Mockito.when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        userService.registerUser("test", "password", "test@test.com");

        Mockito.verify(userRepository).save(any(User.class));
    }

    @Test
    void testRegisterUser_Fail_UsernameAlreadyExists() {
        Mockito.when(userRepository.findByUsername("test")).thenReturn(testUser);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser("test", "password", "test@test.com");
        });
        assertEquals("Username already exists", exception.getMessage());
        Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    }


    @Test
    void testRegisterUser_Fail_EmailAlreadyExists() {
        Mockito.when(userRepository.findByUsername("test")).thenReturn(null);
        Mockito.when(userRepository.findByEmail("test@test.com")).thenReturn(testUser);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser("test", "password", "test@test.com");
        });
        assertEquals("Email already exists", exception.getMessage());
        Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    }

    @Test
    void testLoginUser_Success() {
        Mockito.when(userRepository.findByUsername("test")).thenReturn(testUser);
        Mockito.when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        Optional<User> result = userService.loginUser("test", "password");

        assertTrue(result.isPresent());
        assertEquals("test", result.get().getUsername());
        Mockito.verify(userRepository).save(any(User.class));
    }

    @Test
    void testLoginUser_Fail_WrongPassword() {
        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(testUser);
        Mockito.when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        Optional<User> result = userService.loginUser("testUser", "wrongPassword");

        assertTrue(result.isEmpty());
        Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    }

    @Test
    void testFindByUsername() {
        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(testUser);

        User result = userService.findByUsername("testUser");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }









}
