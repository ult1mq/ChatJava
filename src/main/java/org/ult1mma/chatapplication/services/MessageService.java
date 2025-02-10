package org.ult1mma.chatapplication.services;

import org.springframework.stereotype.Service;
import org.ult1mma.chatapplication.entities.Message;
import org.ult1mma.chatapplication.entities.User;
import org.ult1mma.chatapplication.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessage (User sender, User receiver, String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setSentAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBetweenUsers(User sender, User receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);

    }

    public List<Message> getMessagesForUser (User receiver) {
        return messageRepository.findByReceiver(receiver);
    }
}
