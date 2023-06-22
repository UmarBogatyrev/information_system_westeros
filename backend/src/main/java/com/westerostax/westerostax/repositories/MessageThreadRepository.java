package com.westerostax.westerostax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.westerostax.westerostax.entity.MessageThread;

public interface MessageThreadRepository extends JpaRepository<MessageThread, Long> {
    // Методы для получения информации о связях между пользователями и лордами
}
