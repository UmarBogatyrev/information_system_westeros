package ru.itmo.WesterosTax.controller;

import ru.itmo.WesterosTax.model.District;
import ru.itmo.WesterosTax.model.Region;
import ru.itmo.WesterosTax.model.User;
import ru.itmo.WesterosTax.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Дополнительные методы для работы с пользователями
}