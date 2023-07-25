package itmo.WesterosTax.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itmo.WesterosTax.model.Region;
import itmo.WesterosTax.service.RegionService;

@RestController  
@RequestMapping("/regions")  
public class RegionController {  
    private final RegionService regionService;  
  
    public RegionController(RegionService regionService) {  
        this.regionService = regionService;  
    }  
  
    @GetMapping  
    public List<Region> getAllRegions() {  
        return regionService.getAllRegions();  
    }  
  
    @GetMapping("/{id}")  
    public Optional<Region> getRegionById(@PathVariable Long id) {  
        return regionService.getRegionById(id);  
    }  
  
    @PostMapping  
    public Region saveRegion(@RequestBody Region region) {  
        return regionService.saveRegion(region);  
    }  
  
    @DeleteMapping("/{id}")  
    public void deleteRegion(@PathVariable Long id) {  
        regionService.deleteRegion(id);  
    }  
}  