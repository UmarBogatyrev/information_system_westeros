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

@RequestMapping("/tax")
@Controller
public class TaxController {

    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    private final TaxRepository taxRepository;

    private final TaxRegionRepository taxRegionRepository;

    private final TaxDistrictRepository taxDistrictRepository;

    public TaxController(UserRepository userRepository, RegionRepository regionRepository, TaxRepository taxRepository,
                         TaxRegionRepository taxRegionRepository, TaxDistrictRepository taxDistrictRepository) {
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
        this.taxRepository = taxRepository;
        this.taxRegionRepository = taxRegionRepository;
        this.taxDistrictRepository = taxDistrictRepository;
    }

    @PostMapping("create")
    public String createTax(@ModelAttribute("tax") @Valid Tax tax, BindingResult bindingResultTax) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (bindingResultTax.hasErrors()) {
            return "redirect:/lord/main";
        }
        tax.setFinished(false);
        taxRepository.save(tax);
        for (Region region : regionRepository.findAllByLord(lord)) {
            TaxRegion taxRegion = new TaxRegion("created", region, tax);
            taxRegionRepository.save(taxRegion);
            for (District district : region.getDistricts()) {
                TaxDistrict taxDistrict = new TaxDistrict("created", district, taxRegion);
                taxDistrictRepository.save(taxDistrict);
            }
        }
        return "redirect:/lord/main";
    }

    @PostMapping("createDistrict")
    public String createTaxDistrict(@RequestParam Tax tax) {
        User courier = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Iterable<Household> households = courier.getDistrict().getHouseholds();
        TaxDistrict taxDistrict = taxDistrictRepository.findByTaxRegionTaxAndDistrict(tax, courier.getDistrict());
        String formula = taxDistrict.getTaxRegion().getTax().getTaxType().getFormula();
        for (Household household : households) {
            taxDistrict.setTotalIncome(household.getIncome());
            taxDistrict.setStatus("submitting");
        }
        taxDistrictRepository.save(taxDistrict);
        return "redirect:/household/index";
    }

    @PostMapping("submitDistrict")
    public String submitTaxDistrict(@RequestParam TaxDistrict taxDistrict) {
        TaxRegion taxRegion = taxDistrict.getTaxRegion();
        taxRegion.setTotalIncome(taxRegion.getTotalIncome() + taxDistrict.getTotalIncome());
        taxDistrict.setStatus("submitted");
        taxDistrictRepository.save(taxDistrict);
        return "redirect:/landowner/main";
    }

    @PostMapping("submitRegion")
    public String submitTaxRegion(@RequestParam TaxRegion taxRegion, @RequestParam Tax tax) {
        tax.setTotalIncome(tax.getTotalIncome() + taxRegion.getTotalIncome());
        taxRegion.setStatus("submitted");
        taxRepository.save(tax);
        return "redirect:/lord/main";
    }

    @PostMapping("createRegion")
    public String createTaxRegion(@RequestParam TaxRegion taxRegion) {
        taxRegion.setStatus("submitting");
        taxRegionRepository.save(taxRegion);
        return "redirect:/landowner/main";
    }

    @PostMapping("unsubmitRegion")
    public String unsubmitTaxRegion(@RequestParam TaxRegion taxRegion) {
        taxRegion.setStatus("created");
        taxRegionRepository.save(taxRegion);
        return "redirect:/lord/main";
    }

    @PostMapping("unsubmitDistrict")
    public String unsubmitTaxDistrict(@RequestParam TaxDistrict taxDistrict) {
        taxDistrict.setStatus("created");
        taxDistrictRepository.save(taxDistrict);
        return "redirect:/lord/main";
    }

    @PostMapping("finish")
    public String finishTax(@RequestParam Tax tax) {
        tax.setFinished(true);
        Iterable<TaxRegion> taxRegions = taxRegionRepository.findAllByTaxOrderById(tax);
        for (TaxRegion taxRegion : taxRegions) {
            Iterable<TaxDistrict> taxDistricts = taxDistrictRepository.findAllByTaxRegionOrderById(taxRegion);
            taxDistrictRepository.deleteAll(taxDistricts);
        }
        return "redirect:/lord/main";
    }

    @PostMapping("delete")
    public String deleteTax(@RequestParam Tax tax) {
        Iterable<TaxRegion> taxRegions = taxRegionRepository.findAllByTaxOrderById(tax);
        for (TaxRegion taxRegion : taxRegions) {
            Iterable<TaxDistrict> taxDistricts = taxDistrictRepository.findAllByTaxRegionOrderById(taxRegion);
            taxDistrictRepository.deleteAll(taxDistricts);
        }
        taxRegionRepository.deleteAll(taxRegions);
        taxRepository.delete(tax);
        return "redirect:/lord/main";
    }
}
