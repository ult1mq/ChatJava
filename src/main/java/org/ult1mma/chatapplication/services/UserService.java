package org.ult1mma.chatapplication.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ult1mma.chatapplication.entities.User;
import org.ult1mma.chatapplication.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String password, String email) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);

        userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}