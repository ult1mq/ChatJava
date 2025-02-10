package org.ult1mma.chatapplication.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.ult1mma.chatapplication.entities.User;
import org.ult1mma.chatapplication.services.MessageService;
import org.ult1mma.chatapplication.services.UserService;

@Controller
public class ChatController {

    private final MessageService messageService;
    private final UserService userService;

    public ChatController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/chat")
    public String chatPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("messages", messageService.getMessagesForUser(user));
        return "chat";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String receiverUsername, @RequestParam String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String senderUsername = authentication.getName();
        User sender = userService.findByUsername(senderUsername);
        User receiver = userService.findByUsername(receiverUsername);
        messageService.sendMessage(sender, receiver, content);
        return "redirect:/chat";
    }
}