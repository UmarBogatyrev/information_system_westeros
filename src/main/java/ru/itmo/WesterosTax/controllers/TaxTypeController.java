package ru.itmo.WesterosTax.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.WesterosTax.models.*;
import ru.itmo.WesterosTax.repositories.*;

import javax.validation.Valid;

@RequestMapping("/taxType")
@Controller
public class TaxTypeController {

    private final UserRepository userRepository;

    private final TaxTypeRepository taxTypeRepository;

    private final TaxRepository taxRepository;

    private final TaxRegionRepository taxRegionRepository;

    private final TaxDistrictRepository taxDistrictRepository;

    public TaxTypeController(UserRepository userRepository, TaxTypeRepository taxTypeRepository, TaxRepository taxRepository,
                             TaxRegionRepository taxRegionRepository, TaxDistrictRepository taxDistrictRepository) {
        this.userRepository = userRepository;
        this.taxTypeRepository = taxTypeRepository;
        this.taxRepository = taxRepository;
        this.taxRegionRepository = taxRegionRepository;
        this.taxDistrictRepository = taxDistrictRepository;
    }

    @GetMapping("index")
    public String index(Model model) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("taxTypes", taxTypeRepository.findAllByLord(lord));
        return "lord/tax/Index";
    }

    @GetMapping("landowner/index")
    public String landownerIndex(Model model) {
        User landowner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("taxTypes", taxTypeRepository.findAllByLord(landowner.getLord()));
        return "landowner/tax/Index";
    }

    @GetMapping("create")
    public String create(@ModelAttribute("taxType") TaxType taxType) {
        return "lord/tax/Create";
    }

    @PostMapping("create")
    public String create(@ModelAttribute("taxType") @Valid TaxType taxType, BindingResult bindingResultTax) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (bindingResultTax.hasErrors()) {
            return "lord/tax/Create";
        }
        taxType.setLord(lord);
        taxTypeRepository.save(taxType);
        return "redirect:/taxType/index";
    }

    @GetMapping("edit")
    public String edit(@RequestParam TaxType taxType, Model model) {
        model.addAttribute("taxType", taxType);
        return "lord/tax/Edit";
    }

    @PostMapping("edit")
    public String edit(@ModelAttribute("taxType") @Valid TaxType taxType, BindingResult bindingResultTax) {
        if (bindingResultTax.hasErrors()) {
            return "lord/tax/Edit";
        }
        taxTypeRepository.save(taxType);
        return "redirect:/taxType/index";
    }

    @PostMapping("delete")
    public String delete(@RequestParam TaxType taxType) {
        Iterable<Tax> taxes = taxRepository.findAllByTaxType(taxType);
        for (Tax tax : taxes) {
            Iterable<TaxRegion> taxRegions = taxRegionRepository.findAllByTaxOrderById(tax);
            for (TaxRegion taxRegion : taxRegions) {
                Iterable<TaxDistrict> taxDistricts = taxDistrictRepository.findAllByTaxRegionOrderById(taxRegion);
                taxDistrictRepository.deleteAll(taxDistricts);
            }
            taxRegionRepository.deleteAll(taxRegions);
            taxRepository.delete(tax);
        }
        taxTypeRepository.delete(taxType);
        return "redirect:/taxType/index";
    }
}