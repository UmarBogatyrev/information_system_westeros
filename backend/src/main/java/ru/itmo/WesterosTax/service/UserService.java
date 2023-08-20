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

    public User createUser(String name, String username, String password, String role) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return userRepository.save(user);
    }

    public User createLord(String name, String username, String password, Region region) {
        return createUser(name, username, password, "LORD");
    }

    public User createLandowner(String name, String username, String password, District district, User lord) {
        User landowner = createUser(name, username, password, "LANDOWNER");
        landowner.setDistrict(district);
        landowner.setLord(lord);
        return userRepository.save(landowner);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

}