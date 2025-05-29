package org.ult1mma.chatapplication.controllers.websocket;


import org.ult1mma.chatapplication.dto.ChatMessageDto;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")        // при отправке на /app/chat.sendMessage
    public void sendMessage(@Payload ChatMessageDto message) {
        // здесь можно сохранять в БД через ваш MessageService
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}
