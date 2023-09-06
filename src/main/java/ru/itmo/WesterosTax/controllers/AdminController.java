package ru.itmo.WesterosTax.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmo.WesterosTax.models.District;
import ru.itmo.WesterosTax.models.Region;
import ru.itmo.WesterosTax.models.Role;
import ru.itmo.WesterosTax.models.User;
import ru.itmo.WesterosTax.repositories.DistrictRepository;
import ru.itmo.WesterosTax.repositories.RegionRepository;
import ru.itmo.WesterosTax.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Collections;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final DistrictRepository districtRepository;

    private final RegionRepository regionRepository;

    public AdminController(PasswordEncoder passwordEncoder, UserRepository userRepository, DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("lord")
    public String lord(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("regions", regionRepository.findAll());
        model.addAttribute("districts", districtRepository.findAll());
        return "admin/Lord";
    }

    @GetMapping("district")
    public String district(@ModelAttribute("district") District district, Model model) {
        model.addAttribute("regions", regionRepository.findAll());
        return "admin/District";
    }

    @GetMapping("region")
    public String region(@ModelAttribute("region") Region region) {
        return "admin/Region";
    }

    @PostMapping("registerLord")
    public String registerLord(@ModelAttribute("user") @Valid User user, BindingResult bindingResultUser, Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            bindingResultUser.addError(new ObjectError("username", "Данный логин уже занят"));
            model.addAttribute("usernameError", "Данный логин уже занят");
        }
        if (bindingResultUser.hasErrors()) {
            model.addAttribute("regions", regionRepository.findAll());
            model.addAttribute("districts", districtRepository.findAll());
            return "admin/Lord";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.ROLE_LORD));
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/admin/lord";
    }

    @PostMapping("createDistrict")
    public String createDistrict(@ModelAttribute("district") @Valid District district, BindingResult bindingResulDistrict) {
        if (bindingResulDistrict.hasErrors()) {
            return "admin/District";
        }
        districtRepository.save(district);
        return "redirect:/admin/district";
    }

    @PostMapping("createRegion")
    public String createRegion(@ModelAttribute("region") @Valid Region region, BindingResult bindingResulRegion) {
        if (bindingResulRegion.hasErrors()) {
            return "admin/Region";
        }
        regionRepository.save(region);
        return "redirect:/admin/region";
    }
}
