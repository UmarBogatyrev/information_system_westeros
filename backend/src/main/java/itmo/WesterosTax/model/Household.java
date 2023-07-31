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
@Table(name = "households")  
public class Household {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @Column(nullable = false)  
    private String name;  
  
    @Column(nullable = false)  
    private String address;  
  
    @Column(name = "resident_count", nullable = false)  
    private Integer residentCount;  
  
    @Column(nullable = false)  
    private Double income;  
  
    @Column(name = "land_area", nullable = false)  
    private Double landArea;  
  
    @Column(name = "cattle_headcount", nullable = false)  
    private Integer cattleHeadcount;  
  
    @ManyToOne  
    @JoinColumn(name = "district_id")  
    private District district;  
  
    @Column(name = "taxes_collected", nullable = false)  
    private Double taxesCollected;  
}  