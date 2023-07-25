package itmo.WesterosTax.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itmo.WesterosTax.model.User;
import itmo.WesterosTax.service.UserService;

@RestController  
@RequestMapping("/users")  
public class UserController {  
    private final UserService userService;  
   
    public UserController(UserService userService) {  
        this.userService = userService;  
    }  
    
    @GetMapping  
    public List<User> getAllUsers() {  
        return userService.getAllUsers();  
    }  
    
    @GetMapping("/{id}")  
    public Optional<User> getUserById(@PathVariable Long id) {  
        return userService.getUserById(id);  
    }  
    
    @PostMapping  
    public User saveUser(@RequestBody User user) {  
        return userService.saveUser(user);  
    }  
    
    @DeleteMapping("/{id}")  
    public void deleteUser(@PathVariable Long id) {  
        userService.deleteUser(id);  
    }  
}  