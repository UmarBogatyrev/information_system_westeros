package com.westerostax.westerostax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.westerostax.westerostax.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}