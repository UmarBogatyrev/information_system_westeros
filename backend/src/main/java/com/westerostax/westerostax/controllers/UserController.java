package com.westerostax.westerostax.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.westerostax.westerostax.entity.User;
import com.westerostax.westerostax.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpSession session) {

        if (username.equals("admin") && password.equals("password")) {
            session.setAttribute("username", username);
            return "redirect:/dashboard"; // Перенаправление на страницу панели управления после успешной авторизации
        } else {
            return "redirect:/login?error=1"; // Перенаправление на страницу входа с сообщением об ошибке
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        user.setName(updatedUser.getName());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setDistrictId(updatedUser.getDistrictId());
        user.setRegionId(updatedUser.getRegionId());
        user.setLordId(updatedUser.getLordId());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id.intValue());
    }
}
