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
@Table(name = "districts")  
public class District {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @Column(nullable = false)  
    private String name;  
  
    @Column(nullable = false)
    private int totalHouseholds;

    @Column(nullable = false)
    private int totalCattleHeadcount;

    @Column(nullable = false)
    private int totalResidents;

    @Column(nullable = false)
    private double totalTaxesPaid;  
  
    @ManyToOne  
    @JoinColumn(name = "region_id")  
    private Region region;  
}  