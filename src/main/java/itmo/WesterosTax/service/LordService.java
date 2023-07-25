package itmo.WesterosTax.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import itmo.WesterosTax.model.Lord;
import itmo.WesterosTax.repository.LordRepository;

@Service  
public class LordService {  
    private final LordRepository lordRepository;  

    public LordService(LordRepository lordRepository) {  
        this.lordRepository = lordRepository;  
    }  
  
    public List<Lord> getAllLords() {  
        return lordRepository.findAll();  
    }  
  
    public Optional<Lord> getLordById(Long id) {  
        return lordRepository.findById(id);  
    }  
  
    public Lord saveLord(Lord lord) {  
        return lordRepository.save(lord);  
    }  
  
    public void deleteLord(Long id) {  
        lordRepository.deleteById(id);  
    }  
}  