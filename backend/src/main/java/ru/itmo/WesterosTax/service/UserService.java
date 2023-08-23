package ru.itmo.WesterosTax.service;

import ru.itmo.WesterosTax.model.District;
import ru.itmo.WesterosTax.model.Region;
import ru.itmo.WesterosTax.model.User;
import ru.itmo.WesterosTax.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService  {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // public User createUser(String name, String username, String password, String role) {
    //     User user = new User();
    //     user.setUsername(username);
    //     user.setPassword(password);
    //     return userRepository.save(user);
    // }

}