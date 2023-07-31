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

import itmo.WesterosTax.model.District;
import itmo.WesterosTax.service.DistrictService;

@RestController  
@RequestMapping("/districts")  
public class DistrictController {  
    private final DistrictService districtService;  
  
    public DistrictController(DistrictService districtService) {  
        this.districtService = districtService;  
    }  
  
    @GetMapping  
    public List<District> getAllDistricts() {  
        return districtService.getAllDistricts();  
    }  
  
    @GetMapping("/{id}")  
    public Optional<District> getDistrictById(@PathVariable Long id) {  
        return districtService.getDistrictById(id);  
    }  
  
    @PostMapping  
    public District saveDistrict(@RequestBody District district) {  
        return districtService.saveDistrict(district);  
    }  
  
    @DeleteMapping("/{id}")  
    public void deleteDistrict(@PathVariable Long id) {  
        districtService.deleteDistrict(id);  
    }  
}  