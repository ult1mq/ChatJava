package org.ult1mma.chatapplication.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ult1mma.chatapplication.entities.Message;
import org.ult1mma.chatapplication.entities.User;
import org.ult1mma.chatapplication.repositories.MessageRepository;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testSendMessage() {
        Mockito.when(messageRepository.save(Mockito.any(Message.class))).thenReturn(message);

        Message sentMessage = messageService.sendMessage(sender, receiver, "Hello, Bob!");

        assertNotNull(sentMessage);
        assertEquals("Hello, Bob!", sentMessage.getContent());
        assertEquals(sender, sentMessage.getSender());
        assertEquals(receiver, sentMessage.getReceiver());
        Mockito.verify(messageRepository).save(Mockito.any(Message.class));
    }

    @Test
    void testGetMessagesBetweenUsers() {
        Mockito.when(messageRepository.findBySenderAndReceiver(sender, receiver)).thenReturn(List.of(message));

        List<Message> messages = messageService.getMessagesBetweenUsers(sender, receiver);

        assertFalse(messages.isEmpty());
        assertEquals(1, messages.size());
        assertEquals("Hello, Bob!", messages.get(0).getContent());
        Mockito.verify(messageRepository).findBySenderAndReceiver(sender, receiver);
    }

    @Test
    void testGetMessagesForUser() {
        Mockito.when(messageRepository.findByReceiver(receiver)).thenReturn(List.of(message));

        List<Message> messages = messageService.getMessagesForUser(receiver);

        assertFalse(messages.isEmpty());
        assertEquals(1, messages.size());
        assertEquals("Hello, Bob!", messages.get(0).getContent());
        Mockito.verify(messageRepository).findByReceiver(receiver);
    }




}
