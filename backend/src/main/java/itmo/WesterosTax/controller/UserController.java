package itmo.WesterosTax.controller;

import itmo.WesterosTax.model.District;
import itmo.WesterosTax.model.Region;
import itmo.WesterosTax.model.User;
import itmo.WesterosTax.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createLord")
    public ResponseEntity<User> createLord(@RequestBody CreateUserRequest createUserRequest, Region region) {
        User lord = userService.createLord(createUserRequest.getName(), createUserRequest.getUsername(),
                createUserRequest.getPassword(), region);
        return ResponseEntity.ok(lord);
    }

    @PostMapping("/createLandowner")
    public ResponseEntity<User> createLandowner(@RequestBody CreateUserRequest createUserRequest,
                                                District district, User lord) {
        User landowner = userService.createLandowner(createUserRequest.getName(),
                createUserRequest.getUsername(), createUserRequest.getPassword(), district, lord);
        return ResponseEntity.ok(landowner);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        User authenticatedUser = userService.findByUsernameAndPassword(loginRequest.getUsername(),
                loginRequest.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Дополнительные методы для работы с пользователями
}

class CreateUserRequest {
    private String name;
    private String username;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class LoginRequest {
    private String username;
    private String password;
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
