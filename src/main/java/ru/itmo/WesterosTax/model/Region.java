package ru.itmo.WesterosTax.model;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity  
@Table(name = "regions")  
public class Region {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @Column(nullable = false, unique = true)  
    private String name;  
  
    @Column(name = "total_households")  
    private Integer totalHouseholds;  
  
    @Column(name = "total_cattle_headcount")  
    private Integer totalCattleHeadcount;  
  
    @Column(name = "total_residents")  
    private Integer totalResidents;  
  
    @Column(name = "total_taxes_paid")  
    private Double totalTaxesPaid;  
     
    @OneToMany(mappedBy = "region")
    private List<District> districts;
}  