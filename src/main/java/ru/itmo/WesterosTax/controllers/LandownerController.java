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
import ru.itmo.WesterosTax.repositories.RegionRepository;
import ru.itmo.WesterosTax.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Collections;

@RequestMapping("/landowner")
@Controller
public class LandownerController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    public LandownerController(PasswordEncoder passwordEncoder, UserRepository userRepository, RegionRepository regionRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("index")
    public String index(Model model) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("landowners", userRepository.findByLord(lord));
        return "lord/user/Index";
    }

    @GetMapping("create")
    public String create(@ModelAttribute("user") User user, Model model) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("regions", regionRepository.findAllByLord(lord));
        return "lord/user/Create";
    }

    @PostMapping("createNew")
    public String createLandowner(@ModelAttribute("user") @Valid User user, BindingResult bindingResultUser, Model model) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userRepository.findByUsername(user.getUsername()) != null) {
            bindingResultUser.addError(new ObjectError("username", "Данный логин уже занят"));
            model.addAttribute("usernameError", "Данный логин уже занят");
        }
        if (bindingResultUser.hasErrors()) {
            model.addAttribute("regions", regionRepository.findAllByLord(lord));
            return "lord/user/Create";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.ROLE_LANDOWNER));
        user.setLord(lord);
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/landowner/index";
    }

    @PostMapping("delete")
    public String deleteLandowner(@RequestParam User user) {
        userRepository.delete(user);
        return "redirect:/lord/index";
    }
}