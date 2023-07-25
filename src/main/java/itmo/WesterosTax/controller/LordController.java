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

import itmo.WesterosTax.model.Lord;
import itmo.WesterosTax.service.LordService;

@RestController  
@RequestMapping("/lords")  
public class LordController {  
    private final LordService lordService;  
  
    public LordController(LordService lordService) {  
        this.lordService = lordService;  
    }  
    
    @GetMapping  
    public List<Lord> getAllLords() {  
        return lordService.getAllLords();  
    }  
    
    @GetMapping("/{id}")  
    public Optional<Lord> getLordById(@PathVariable Long id) {  
        return lordService.getLordById(id);  
    }  
    
    @PostMapping  
    public Lord saveLord(@RequestBody Lord lord) {  
        return lordService.saveLord(lord);  
    }  
    
    @DeleteMapping("/{id}")  
    public void deleteLord(@PathVariable Long id) {  
        lordService.deleteLord(id);  
    }  
}  