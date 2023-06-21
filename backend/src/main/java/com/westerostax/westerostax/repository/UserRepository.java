package com.westerostax.westerostax.repository;

import com.westerostax.westerostax.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
