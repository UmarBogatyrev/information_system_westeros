package ru.itmo.WesterosTax.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmo.WesterosTax.models.District;
import ru.itmo.WesterosTax.repositories.DistrictRepository;

import java.util.List;

@Controller
@RequestMapping("/district")
public class DistrictController {

    private final DistrictRepository districtRepository;

    public DistrictController(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @GetMapping
    public String getAllDistricts(Model model) {
        model.addAttribute("districts",districtRepository.findAll());
        return "districts/Index";
    }

    @PostMapping("get")
    public District getDistrict(@RequestParam District district) {
        return district;
    }

    @PostMapping("save")
    public District saveDistrict(@RequestParam District district) {
        return districtRepository.save(district);
    }

    @DeleteMapping("delete")
    public void deleteDistrict(@RequestParam District district) {
        districtRepository.delete(district);
    }
}
