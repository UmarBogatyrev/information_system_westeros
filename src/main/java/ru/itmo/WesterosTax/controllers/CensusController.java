package ru.itmo.WesterosTax.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.WesterosTax.models.*;
import ru.itmo.WesterosTax.repositories.*;

import javax.validation.Valid;

@RequestMapping("/census")
@Controller
public class CensusController {

    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    private final CensusRepository censusRepository;

    private final CensusRegionRepository censusRegionRepository;

    private final CensusDistrictRepository censusDistrictRepository;

    public CensusController(UserRepository userRepository, RegionRepository regionRepository, CensusRepository censusRepository,
                            CensusRegionRepository censusRegionRepository, CensusDistrictRepository censusDistrictRepository) {
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
        this.censusRepository = censusRepository;
        this.censusRegionRepository = censusRegionRepository;
        this.censusDistrictRepository = censusDistrictRepository;
    }

    @PostMapping("create")
    public String createCensus(@ModelAttribute("census") @Valid Census census, BindingResult bindingResultCensus) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (bindingResultCensus.hasErrors()) {
            return "redirect:/lord/main";
        }
        census.setLord(lord);
        census.setFinished(false);
        censusRepository.save(census);
        for (Region region : regionRepository.findAllByLord(lord)) {
            CensusRegion censusRegion = new CensusRegion("created", region, census);
            censusRegionRepository.save(censusRegion);
            for (District district : region.getDistricts()) {
                CensusDistrict censusDistrict = new CensusDistrict("created", district, censusRegion);
                censusDistrictRepository.save(censusDistrict);
            }
        }
        return "redirect:/lord/main";
    }

    @PostMapping("createDistrict")
    public String createCensusDistrict(@RequestParam Census census) {
        User courier = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Iterable<Household> households = courier.getDistrict().getHouseholds();
        CensusDistrict censusDistrict = censusDistrictRepository.findByCensusRegionCensusAndDistrict(census, courier.getDistrict());
        for (Household household : households) {
            censusDistrict.setTotalLandArea(censusDistrict.getTotalLandArea() + household.getLandArea());
            censusDistrict.setTotalCattleHeadcount(censusDistrict.getTotalCattleHeadcount() + household.getCattleHeadcount());
            censusDistrict.setTotalResidentCount(censusDistrict.getTotalResidentCount() + household.getResidentCount());
            censusDistrict.setStatus("submitting");
        }
        censusDistrictRepository.save(censusDistrict);
        return "redirect:/household/index";
    }

    @PostMapping("createRegion")
    public String createCensusRegion(@RequestParam CensusRegion censusRegion) {
        censusRegion.setStatus("submitting");
        censusRegionRepository.save(censusRegion);
        return "redirect:/landowner/main";
    }

    @PostMapping("submitRegion")
    public String submitCensusRegion(@RequestParam CensusRegion censusRegion, @RequestParam Census census) {
        census.setTotalLandArea(census.getTotalLandArea() + censusRegion.getTotalLandArea());
        census.setTotalCattleHeadcount(census.getTotalCattleHeadcount() + censusRegion.getTotalCattleHeadcount());
        census.setTotalResidentCount(census.getTotalResidentCount() + censusRegion.getTotalResidentCount());
        census.setTotalHouseholds(census.getTotalHouseholds() + censusRegion.getRegion().getTotalHouseholds());
        censusRegion.setStatus("submitted");
        censusRepository.save(census);
        return "redirect:/lord/main";
    }

    @PostMapping("unsubmitRegion")
    public String unsubmitCensusRegion(@RequestParam CensusRegion censusRegion) {
        censusRegion.setStatus("created");
        censusRegionRepository.save(censusRegion);
        return "redirect:/lord/main";
    }

    @PostMapping("submitDistrict")
    public String submitCensusDistrict(@RequestParam CensusDistrict censusDistrict) {
        CensusRegion censusRegion = censusDistrict.getCensusRegion();
        censusRegion.setTotalLandArea(censusRegion.getTotalLandArea() + censusDistrict.getTotalLandArea());
        censusRegion.setTotalCattleHeadcount(censusRegion.getTotalCattleHeadcount() + censusDistrict.getTotalCattleHeadcount());
        censusRegion.setTotalResidentCount(censusRegion.getTotalResidentCount() + censusDistrict.getTotalResidentCount());
        censusDistrict.setStatus("submitted");
        censusDistrictRepository.save(censusDistrict);
        return "redirect:/landowner/main";
    }

    @PostMapping("unsubmitDistrict")
    public String unsubmitCensusDistrict(@RequestParam CensusDistrict censusDistrict) {
        censusDistrict.setStatus("created");
        censusDistrictRepository.save(censusDistrict);
        return "redirect:/landowner/main";
    }

    @PostMapping("finish")
    public String finishCensus(@RequestParam Census census) {
        census.setFinished(true);
        Iterable<CensusRegion> censusRegions = censusRegionRepository.findAllByCensusOrderById(census);
        for (CensusRegion censusRegion : censusRegions) {
            Iterable<CensusDistrict> censusDistricts = censusDistrictRepository.findAllByCensusRegionOrderById(censusRegion);
            censusDistrictRepository.deleteAll(censusDistricts);
        }
        return "redirect:/lord/main";
    }

    @PostMapping("delete")
    public String deleteCensus(@RequestParam Census census) {
        Iterable<CensusRegion> censusRegions = censusRegionRepository.findAllByCensusOrderById(census);
        for (CensusRegion censusRegion : censusRegions) {
            Iterable<CensusDistrict> censusDistricts = censusDistrictRepository.findAllByCensusRegionOrderById(censusRegion);
            censusDistrictRepository.deleteAll(censusDistricts);
        }
        censusRegionRepository.deleteAll(censusRegions);
        censusRepository.delete(census);
        return "redirect:/lord/main";
    }
}