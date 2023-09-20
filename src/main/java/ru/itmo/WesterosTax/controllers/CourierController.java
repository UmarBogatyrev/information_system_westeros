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

@RequestMapping("/courier")
@Controller
public class CourierController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public CourierController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("index")
    public String index(Model model) {
        User landowner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("couriers", userRepository.findByLandowner(landowner));
        return "landowner/user/Index";
    }

    @GetMapping("create")
    public String create(@ModelAttribute("user") User user, Model model) {
        User  landowner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("districts", landowner.getRegion().getDistricts());
        return "landowner/user/Create";
    }

    @PostMapping("createNew")
    public String createCourier(@ModelAttribute("user") @Valid User user, BindingResult bindingResultUser, Model model) {
        User  landowner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userRepository.findByUsername(user.getUsername()) != null) {
            bindingResultUser.addError(new ObjectError("username", "Данный логин уже занят"));
            model.addAttribute("usernameError", "Данный логин уже занят");
        }
        if (bindingResultUser.hasErrors()) {
            model.addAttribute("districts", landowner.getRegion().getDistricts());
            return "landowner/user/Create";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.ROLE_COURIER));
        user.setLandowner(landowner);
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/courier/index";
    }

    @PostMapping("delete")
    public String deleteCourier(@RequestParam User user) {
        userRepository.delete(user);
        return "redirect:/courier/index";
    }
}