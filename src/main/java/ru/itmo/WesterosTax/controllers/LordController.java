package ru.itmo.WesterosTax.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.itmo.WesterosTax.models.Role;
import ru.itmo.WesterosTax.models.User;
import ru.itmo.WesterosTax.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Collections;

@RequestMapping("/lord")
@Controller
public class LordController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public LordController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("index")
    public String index(Model model) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("landowners", userRepository.findByLord(lord));
        return "lord/user/Index";
    }

    @GetMapping("create")
    public String create(@ModelAttribute("user") User user) {
        return "lord/user/Create";
    }

    @PostMapping("landowner/create")
    public String createLandowner(@ModelAttribute("user") @Valid User user, BindingResult bindingResultUser, Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            bindingResultUser.addError(new ObjectError("username", "Данный логин уже занят"));
            model.addAttribute("usernameError", "Данный логин уже занят");
        }
        if (bindingResultUser.hasErrors()) {
            return "lord/user/Create";
        }
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegion(lord.getRegion());
        user.setDistrict(lord.getDistrict());
        user.setRoles(Collections.singleton(Role.ROLE_LANDOWNER));
        user.setLord(lord);
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/lord/index";
    }

    @PostMapping("landowner/delete")
    public String deleteLandowner(@RequestParam User user) {
        userRepository.delete(user);
        return "redirect:/lord/index";
    }
}
