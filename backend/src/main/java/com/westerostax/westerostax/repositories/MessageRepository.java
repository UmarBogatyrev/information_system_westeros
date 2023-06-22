package com.westerostax.westerostax.repositories;

import com.westerostax.westerostax.entity.Lord;
import com.westerostax.westerostax.entity.Message;
import com.westerostax.westerostax.entity.MessageThread;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByRecipientOrderBySentAtDesc(Lord recipient);

    List<Message> findByThread(MessageThread thread);
}
