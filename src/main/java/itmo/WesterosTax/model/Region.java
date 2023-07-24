package itmo.WesterosTax.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    
    // Также можно добавить связи с другими таблицами через аннотации @OneToMany, @OneToOne, @ManyToOne, @ManyToMany и т.д.  
}  