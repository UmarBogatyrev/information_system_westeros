package ru.itmo.WesterosTax.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.WesterosTax.models.*;
import ru.itmo.WesterosTax.repositories.*;

import javax.validation.Valid;

@RequestMapping("/household")
@Controller
public class HouseholdController {

    private final UserRepository userRepository;

    private final HouseholdRepository householdRepository;

    private final DistrictRepository districtRepository;

    private final RegionRepository regionRepository;

    private final CensusRepository censusRepository;

    private final CensusDistrictRepository censusDistrictRepository;

    private final TaxDistrictRepository taxDistrictRepository;

    private final TaxRepository taxRepository;

    public HouseholdController(UserRepository userRepository, HouseholdRepository householdRepository, DistrictRepository districtRepository,
                               RegionRepository regionRepository, CensusRepository censusRepository, CensusDistrictRepository censusDistrictRepository,
                               TaxDistrictRepository taxDistrictRepository, TaxRepository taxRepository) {
        this.userRepository = userRepository;
        this.householdRepository = householdRepository;
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
        this.censusRepository = censusRepository;
        this.censusDistrictRepository = censusDistrictRepository;
        this.taxDistrictRepository = taxDistrictRepository;
        this.taxRepository = taxRepository;
    }

    @GetMapping("index")
    public String index(Model model) {
        User courier = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        User lord = courier.getLandowner().getLord();
        Tax unfinishedTax = taxRepository.findByTaxTypeLordAndFinished(lord, false);
        Census unfinishedCensus = censusRepository.findByLordAndFinished(lord, false);
        if (unfinishedCensus != null) {
            model.addAttribute("unfinishedCensus", unfinishedCensus);
        }
        if (unfinishedTax != null) {
            model.addAttribute("unfinishedTax", unfinishedTax);
        }
        CensusDistrict censusDistrict = censusDistrictRepository.findByCensusRegionCensusAndDistrict(unfinishedCensus, courier.getDistrict());
        TaxDistrict taxDistrict = taxDistrictRepository.findByTaxRegionTaxAndDistrict(unfinishedTax, courier.getDistrict());
        model.addAttribute("households", courier.getDistrict().getHouseholds());
        model.addAttribute("censusDistrict", censusDistrict);
        model.addAttribute("taxDistrict", taxDistrict);
        return "courier/household/Index";
    }

    @GetMapping("create")
    public String create(@ModelAttribute("household") Household household) {
        return "courier/household/Create";
    }

    @GetMapping("edit")
    public String edit(@RequestParam Household household, Model model) {
        model.addAttribute("household", household);
        return "courier/household/Edit";
    }

    @PostMapping("create")
    public String createHousehold(@ModelAttribute("household") @Valid Household household, BindingResult bindingResultHousehold) {
        if (bindingResultHousehold.hasErrors()) {
            return "courier/household/Create";
        }
        User courier = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        household.setDistrict(courier.getDistrict());
        householdRepository.save(household);
        District district = courier.getDistrict();
        Region region = district.getRegion();
//        district.setTotalHouseholds(district.getTotalHouseholds() + 1);
//        district.setTotalCattleHeadcount(household.getCattleHeadcount());
//        district.setTotalResidents(house);
        districtRepository.save(district);
        regionRepository.save(region);
        return "redirect:/household/index";
    }

    @PostMapping("edit")
    public String editHousehold(@ModelAttribute("household") @Valid Household household, BindingResult bindingResultHousehold) {
        if (bindingResultHousehold.hasErrors()) {
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