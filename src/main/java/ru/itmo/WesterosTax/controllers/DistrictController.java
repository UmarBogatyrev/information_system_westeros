package ru.itmo.WesterosTax.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.WesterosTax.models.District;
import ru.itmo.WesterosTax.repositories.DistrictRepository;
import ru.itmo.WesterosTax.repositories.HouseholdRepository;

@Controller
@RequestMapping("/district")
public class DistrictController {

    private final DistrictRepository districtRepository;

    private final HouseholdRepository householdRepository;

    public DistrictController(DistrictRepository districtRepository, HouseholdRepository householdRepository) {
        this.districtRepository = districtRepository;
        this.householdRepository = householdRepository;
    }

    @PostMapping("save")
    public District saveDistrict(@RequestParam District district) {
        return districtRepository.save(district);
    }

    @DeleteMapping("delete")
    public void deleteDistrict(@RequestParam District district) {
        householdRepository.deleteAll(district.getHouseholds());
        districtRepository.delete(district);
    }
}