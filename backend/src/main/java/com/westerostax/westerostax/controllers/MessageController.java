package com.westerostax.westerostax.controllers;

import com.westerostax.westerostax.entity.Message;
import com.westerostax.westerostax.entity.User;
import com.westerostax.westerostax.services.UserService;
import com.westerostax.westerostax.entity.MessageThread;
import com.westerostax.westerostax.entity.Lord;
import com.westerostax.westerostax.services.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController<userService> {
    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/threads")
    public ResponseEntity<MessageThread> createMessageThread(@RequestParam Long userId, @RequestParam Long lordId) {
        User user = userService.getUserById(userId);
        Lord lord = userService.getLordById(lordId);
        MessageThread thread = messageService.createMessageThread(user, lord);
        return ResponseEntity.ok(thread);
    }

    @PostMapping("/{threadId}/send")
    public ResponseEntity<Message> sendMessage(
            @PathVariable Long threadId,
            @RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam String subject,
            @RequestParam String content
    ) {
        MessageThread thread = messageService.getMessageThreadById(threadId);
        User sender = userService.getUserById(senderId);
        Lord recipient = userService.getLordById(recipientId);
        Message message = messageService.sendMessage(thread, sender, recipient, subject, content);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{threadId}/messages")
    public ResponseEntity<List<Message>> getMessagesByThread(@PathVariable Long threadId) {
        MessageThread thread = messageService.getMessageThreadById(threadId);
        List<Message> messages = messageService.getMessagesByThread(thread);
        return ResponseEntity.ok(messages);
    }

    // Другие методы для взаимодействия с сообщениями
}

