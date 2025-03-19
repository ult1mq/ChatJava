package org.ult1mma.chatapplication.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ult1mma.chatapplication.entities.Message;
import org.ult1mma.chatapplication.entities.User;
import org.ult1mma.chatapplication.repositories.MessageRepository;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    private User sender;
    private User receiver;
    private Message message;

    @BeforeEach
    void setUp() {
        sender = new User();
        sender.setUsername("Alice");

        receiver = new User();
        receiver.setUsername("Bob");

        message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent("Hello, Bob!");
        message.setSentAt(LocalDateTime.now());
    }

}
