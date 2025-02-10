package org.ult1mma.chatapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ult1mma.chatapplication.entities.Message;
import org.ult1mma.chatapplication.entities.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiver(User sender, User receiver);
    List<Message> findByReceiver(User receiver);
}