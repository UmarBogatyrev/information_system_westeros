package com.westerostax.westerostax.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.westerostax.westerostax.entity.Household;
import com.westerostax.westerostax.repositories.HouseholdRepository;

@Service
public class HouseholdService {
    private final HouseholdRepository householdRepository;

    public HouseholdService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    public Household getHouseholdById(int id) {
        return householdRepository.findById(id).orElse(null);
    }

    public Household saveHousehold(Household household) {
        return householdRepository.save(household);
    }

    public void deleteHousehold(int id) {
        householdRepository.deleteById(id);
    }
}