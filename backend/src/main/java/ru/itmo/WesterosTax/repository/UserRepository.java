package ru.itmo.WesterosTax.repository;

import ru.itmo.WesterosTax.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    List<User> findByRole(String role);
}
