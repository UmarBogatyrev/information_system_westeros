package itmo.WesterosTax.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import itmo.WesterosTax.model.Household;
import itmo.WesterosTax.repository.HouseholdRepository;

@Service  
public class HouseholdService {  
    private final HouseholdRepository householdRepository;  
   
    public HouseholdService(HouseholdRepository householdRepository) {  
        this.householdRepository = householdRepository;  
    }  
  
    public List<Household> getAllHouseholds() {  
        return householdRepository.findAll();  
    }  
  
    public Optional<Household> getHouseholdById(Long id) {  
        return householdRepository.findById(id);  
    }  
  
    public Household saveHousehold(Household household) {  
        return householdRepository.save(household);  
    }  
  
    public void deleteHousehold(Long id) {  
        householdRepository.deleteById(id);  
    }  
}  