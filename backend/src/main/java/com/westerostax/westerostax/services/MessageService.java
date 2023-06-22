package com.westerostax.westerostax.services;

import com.westerostax.westerostax.entity.Lord;
import com.westerostax.westerostax.entity.Message;
import com.westerostax.westerostax.entity.MessageThread;
import com.westerostax.westerostax.entity.User;
import com.westerostax.westerostax.repositories.MessageRepository;
import com.westerostax.westerostax.repositories.MessageThreadRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MessageService {
    private final MessageThreadRepository threadRepository;
    private final MessageRepository messageRepository;

    public MessageService(MessageThreadRepository threadRepository, MessageRepository messageRepository) {
        this.threadRepository = threadRepository;
        this.messageRepository = messageRepository;
    }

    public MessageThread createMessageThread(User user, Lord lord) {
        MessageThread thread = new MessageThread();
        thread.setUser(user);
        thread.setLord(lord);
        return threadRepository.save(thread);
    }

    public Message sendMessage(MessageThread thread, User sender, Lord recipient, String subject, String content) {
        Message message = new Message();
        message.setThread(thread);
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setSubject(subject);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByThread(MessageThread thread) {
        return messageRepository.findByThread(thread);
    }

    public MessageThread getMessageThreadById(Long threadId) {
        return null;
    }

    // Другие методы для управления сообщениями
}
