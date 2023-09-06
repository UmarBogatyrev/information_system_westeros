package ru.itmo.WesterosTax.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.WesterosTax.models.Role;
import ru.itmo.WesterosTax.models.User;
import ru.itmo.WesterosTax.repositories.UserRepository;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public String getMainPage() {
        return switch (getRoleType(SecurityContextHolder.getContext().getAuthentication())) {
            case ROLE_LORD -> "redirect:/lord/index";
            case ROLE_LANDOWNER -> "redirect:/landowner/index";
            case ROLE_COURIER -> "redirect:/household/index";
            case ROLE_ADMIN -> "redirect:/admin/lord";
        };
    }

    @GetMapping("/")
    public String postMainPage() {
        return switch (getRoleType(SecurityContextHolder.getContext().getAuthentication())) {
            case ROLE_LORD -> "redirect:/lord/index";
            case ROLE_LANDOWNER -> "redirect:/landowner/index";
            case ROLE_COURIER -> "redirect:/household/index";
            case ROLE_ADMIN -> "redirect:/admin/lord";
        };
    }

    public Role getRoleType(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        return (Role) user.getRoles().toArray()[0];
    }
}
