package itmo.WesterosTax.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import itmo.WesterosTax.model.Region;
import itmo.WesterosTax.repository.RegionRepository;

@Service  
public class RegionService {  
    private final RegionRepository regionRepository;  
  
    public RegionService(RegionRepository regionRepository) {  
        this.regionRepository = regionRepository;  
    }  
  
    public List<Region> getAllRegions() {  
        return regionRepository.findAll();  
    }  
  
    public Optional<Region> getRegionById(Long id) {  
        return regionRepository.findById(id);  
    }  
  
    public Region saveRegion(Region region) {  
        return regionRepository.save(region);  
    }  
  
    public void deleteRegion(Long id) {  
        regionRepository.deleteById(id);  
    }  
}  