package com.westerostax.westerostax.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.westerostax.westerostax.entity.Household;
import com.westerostax.westerostax.repositories.HouseholdRepository;

@Controller
@RestController
@RequestMapping("/households")
public class HouseholdController {
    private final HouseholdRepository householdRepository;

    public HouseholdController(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @GetMapping("/table")
    public ModelAndView getHouseholdTable() {
        List<Household> households = householdRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("table"); // Имя представления
        modelAndView.addObject("households", households);
        return modelAndView;
    }

    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @GetMapping("/{id}")
    public Household getHouseholdById(@PathVariable("id") Long id) {
        return householdRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Invalid household ID: " + id));
    }

    @PostMapping
    public Household createHousehold(@RequestBody Household household) {
        return householdRepository.save(household);
    }

    @PutMapping("/{id}")
    public Household updateHousehold(@PathVariable("id") Long id, @RequestBody Household updatedHousehold) {
        Household household = householdRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Invalid household ID: " + id));
        household.setName(updatedHousehold.getName());
        household.setAddress(updatedHousehold.getAddress());
        household.setResidentCount(updatedHousehold.getResidentCount());
        household.setIncome(updatedHousehold.getIncome());
        household.setLandArea(updatedHousehold.getLandArea());
        household.setCattleHeadcount(updatedHousehold.getCattleHeadcount());
        household.setDistrictId(updatedHousehold.getDistrictId());
        household.setTaxesCollected(updatedHousehold.getTaxesCollected());
        return householdRepository.save(household);
    }

    @DeleteMapping("/{id}")
    public void deleteHousehold(@PathVariable("id") Long id) {
        householdRepository.deleteById(id.intValue());
    }
}
