package ru.itmo.WesterosTax.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.WesterosTax.model.Household;
import ru.itmo.WesterosTax.service.HouseholdService;

@RestController  
@RequestMapping("/households")  
public class HouseholdController {  
    private final HouseholdService householdService;  
  
    public HouseholdController(HouseholdService householdService) {  
        this.householdService = householdService;  
    }  
    
    @GetMapping  
    public List<Household> getAllHouseholds() {  
        return householdService.getAllHouseholds();  
    }  
    
    @GetMapping("/{id}")  
    public Optional<Household> getHouseholdById(@PathVariable Long id) {  
        return householdService.getHouseholdById(id);  
    }  
    
    @PostMapping  
    public Household saveHousehold(@RequestBody Household household) {  
        return householdService.saveHousehold(household);  
    }  
    
    @DeleteMapping("/{id}")  
    public void deleteHousehold(@PathVariable Long id) {  
        householdService.deleteHousehold(id);  
    }  
}  