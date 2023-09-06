package ru.itmo.WesterosTax.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.WesterosTax.models.Household;
import ru.itmo.WesterosTax.repositories.DistrictRepository;
import ru.itmo.WesterosTax.repositories.HouseholdRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/household")
public class HouseholdController {

    private final HouseholdRepository householdRepository;

    private final DistrictRepository districtRepository;

    public HouseholdController(HouseholdRepository householdRepository, DistrictRepository districtRepository) {
        this.householdRepository = householdRepository;
        this.districtRepository = districtRepository;
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("households", householdRepository.findAll());
        return "courier/household/Index";
    }

    @GetMapping("create")
    public String create(@ModelAttribute("household") Household household, Model model) {
        model.addAttribute("districts", districtRepository.findAll());
        return "courier/household/Create";
    }

    @GetMapping("edit")
    public String edit(@RequestParam Household household, Model model) {
        model.addAttribute("household", household);
        model.addAttribute("districts", districtRepository.findAll());
        return "courier/household/Edit";
    }

    @PostMapping("create")
    public String createHousehold(@ModelAttribute("household") @Valid Household household, BindingResult bindingResultHousehold, Model model) {
        if (bindingResultHousehold.hasErrors()) {
            model.addAttribute("districts", districtRepository.findAll());
            return "courier/household/Create";
        }
        householdRepository.save(household);
        return "redirect:/household/index";
    }

    @PostMapping("edit")
    public String editHousehold(@ModelAttribute("household") @Valid Household household, BindingResult bindingResultHousehold, Model model) {
        if (bindingResultHousehold.hasErrors()) {
            model.addAttribute("districts", districtRepository.findAll());
            return "courier/household/Edit";
        }
        householdRepository.save(household);
        return "redirect:/household/index";
    }

    @PostMapping("delete")
    public String deleteHousehold(@RequestParam Household household) {
        householdRepository.delete(household);
        return "redirect:/household/index";
    }
}
