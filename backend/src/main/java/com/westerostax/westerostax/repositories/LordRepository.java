package com.westerostax.westerostax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.westerostax.westerostax.entity.Lord;

public interface LordRepository extends JpaRepository<Lord, Integer> {

}