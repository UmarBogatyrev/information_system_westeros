package ru.itmo.WesterosTax.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmo.WesterosTax.models.*;
import ru.itmo.WesterosTax.repositories.*;

@Controller
public class PageController {

    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    private final CensusRepository censusRepository;

    private final CensusRegionRepository censusRegionRepository;

    private final CensusDistrictRepository censusDistrictRepository;

    private final TaxRegionRepository taxRegionRepository;

    private final TaxDistrictRepository taxDistrictRepository;

    private final TaxRepository taxRepository;

    private final TaxTypeRepository taxTypeRepository;

    public PageController(UserRepository userRepository, RegionRepository regionRepository, CensusRepository censusRepository, TaxRepository taxRepository,
                          TaxTypeRepository taxTypeRepository, CensusRegionRepository censusRegionRepository, CensusDistrictRepository censusDistrictRepository,
                          TaxRegionRepository taxRegionRepository, TaxDistrictRepository taxDistrictRepository) {
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
        this.censusRepository = censusRepository;
        this.taxRepository = taxRepository;
        this.taxTypeRepository = taxTypeRepository;
        this.censusRegionRepository = censusRegionRepository;
        this.censusDistrictRepository = censusDistrictRepository;
        this.taxRegionRepository = taxRegionRepository;
        this.taxDistrictRepository = taxDistrictRepository;
    }

    @GetMapping("/lord/main")
    public String lordMain(@ModelAttribute("tax") Tax tax, @ModelAttribute("census") Census census, Model model) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Tax unfinishedTax = taxRepository.findByTaxTypeLordAndFinished(lord, false);
        Census unfinishedCensus = censusRepository.findByLordAndFinished(lord, false);
        Census censusLast = censusRepository.findFirstByLordOrderByIdDesc(lord);
        Tax taxLast = taxRepository.findFirstByTaxTypeLordOrderByIdDesc(lord);

        model.addAttribute("unfinishedCensus", unfinishedCensus);
        model.addAttribute("censusRegions", censusRegionRepository.findAllByCensusOrderById(unfinishedCensus));

        model.addAttribute("unfinishedTax", unfinishedTax);
        model.addAttribute("taxRegions", taxRegionRepository.findAllByTaxOrderById(unfinishedTax));

        if (unfinishedTax != null) {
            Long taxTypeId = unfinishedTax.getTaxType().getId();
            String formula = taxTypeRepository.findFormulaById(taxTypeId);
            int formulaInt = Integer.parseInt(formula);
            model.addAttribute("taxFormula", formulaInt);
        }

        model.addAttribute("censusLast", censusLast);
        model.addAttribute("taxLast", taxLast);
        model.addAttribute("taxTypes", taxTypeRepository.findAllByLord(lord));
        return "lord/Main";
    }

    @GetMapping("/landowner/main")
    public String landownerMain(Model model) {
        User landowner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Tax unfinishedTax = taxRepository.findByTaxTypeLordAndFinished(landowner.getLord(), false);
        Census unfinishedCensus = censusRepository.findByLordAndFinished(landowner.getLord(), false);
        CensusRegion censusRegionLast = censusRegionRepository.findFirstByRegionOrderByIdDesc(landowner.getRegion());
        TaxRegion taxRegionLast = taxRegionRepository.findFirstByRegionOrderByIdDesc(landowner.getRegion());

        if (unfinishedTax != null) {
            Long taxTypeId = unfinishedTax.getTaxType().getId();
            String formula = taxTypeRepository.findFormulaById(taxTypeId);
            int formulaInt = Integer.parseInt(formula);
            model.addAttribute("taxFormula", formulaInt);
        }

        CensusRegion censusRegion = censusRegionRepository.findByCensusAndRegion(unfinishedCensus, landowner.getRegion());
        model.addAttribute("unfinishedCensus", unfinishedCensus);
        model.addAttribute("censusRegion", censusRegion);
        model.addAttribute("censusDistricts", censusDistrictRepository.findAllByCensusRegionOrderById(censusRegion));

        TaxRegion taxRegion = taxRegionRepository.findByTaxAndRegion(unfinishedTax, landowner.getRegion());
        model.addAttribute("unfinishedTax", unfinishedTax);
        model.addAttribute("taxRegion", taxRegion);
        model.addAttribute("taxDistricts", taxDistrictRepository.findAllByTaxRegionOrderById(taxRegion));

        model.addAttribute("taxRegionLast", taxRegionLast);
        model.addAttribute("censusRegionLast", censusRegionLast);
        return "landowner/Main";
    }

    @GetMapping("/lord/analytics")
    public String lordAnalytics(Model model) {
        User lord = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Iterable<Region> regions = regionRepository.findAllByLord(lord);
        model.addAttribute("regions", regions);
        return "lord/Analytics";
    }

    @GetMapping("/landowner/analytics")
    public String landownerAnalytics(Model model) {
        User landowner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("districts", landowner.getRegion().getDistricts());
        return "landowner/Analytics";
    }
}