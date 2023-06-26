package com.westerostax.westerostax.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.westerostax.westerostax.entity.Lord;
import com.westerostax.westerostax.entity.User;
import com.westerostax.westerostax.repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public List<User> getUsersByDistrict(int districtId) {
        return userRepository.getUsersByDistrict(districtId);
    }

    public List<User> getUsersByRegion(int regionId) {
        return userRepository.getUsersByRegion(regionId);
    }

    public List<User> getUsersByLord(int lordId) {
        return userRepository.getUsersByLord(lordId);
    }

    public void createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.createUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public User getUserById(Long userId) {
        return null;
    }

    public Lord getLordById(Long lordId) {
        return null;
    }

    private PasswordEncoder passwordEncoder;

    public User authenticateUser(String username, String password) {
        User user = userRepository.getUserByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }
}
