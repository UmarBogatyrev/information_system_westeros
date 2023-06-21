package com.westerostax.westerostax.controllers;

import com.westerostax.westerostax.models.Household;
import com.westerostax.westerostax.repository.HouseholdRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/households")
public class HouseholdController {
    private final HouseholdRepository householdRepository;

    public HouseholdController(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
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
