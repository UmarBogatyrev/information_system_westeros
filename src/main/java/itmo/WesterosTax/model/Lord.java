package itmo.WesterosTax.model;


import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;  
  
@Entity  
@Table(name = "lords")  
public class Lord {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @Column(nullable = false)  
    private String name;  
  
    @Column(nullable = false, unique = true)  
    private String username;  
  
    @Column(nullable = false)  
    private String password;  
  
    @ManyToOne  
    @JoinColumn(name = "region_id")  
    private Region region;  
}  