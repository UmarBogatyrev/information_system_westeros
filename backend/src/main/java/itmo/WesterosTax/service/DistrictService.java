package itmo.WesterosTax.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import itmo.WesterosTax.model.District;
import itmo.WesterosTax.repository.DistrictRepository;

@Service  
public class DistrictService {  
    private final DistrictRepository districtRepository;  
  
    public DistrictService(DistrictRepository districtRepository) {  
        this.districtRepository = districtRepository;  
    }  
  
    public List<District> getAllDistricts() {  
        return districtRepository.findAll();  
    }  
  
    public Optional<District> getDistrictById(Long id) {  
        return districtRepository.findById(id);  
    }  
  
    public District saveDistrict(District district) {  
        return districtRepository.save(district);  
    }  
  
    public void deleteDistrict(Long id) {  
        districtRepository.deleteById(id);  
    }  
}  