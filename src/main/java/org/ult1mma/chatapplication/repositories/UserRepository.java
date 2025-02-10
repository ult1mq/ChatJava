package org.ult1mma.chatapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ult1mma.chatapplication.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    // Find a user by their email address
    User findByEmail(String email);

    // Find all active users
    List<User> findAllByIsActiveTrue();

    // Find users by their last login date
    List<User> findByLastLoginAfter(LocalDateTime lastLogin);
}