package ru.itmo.WesterosTax.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 50, message = "Название округа должно быть от 1 до 50 символов")
    private String name;

    @NotNull
    private int totalHouseholds;

    @NotNull
    private int totalCattleHeadcount;

    @NotNull
    private int totalResidents;

    @NotNull
    private double totalIncome;

    @NotNull
    private double totalTaxesPaid;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @OneToMany(mappedBy = "district")
    private List<Household> households;

    public District() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalHouseholds() {
        return totalHouseholds;
    }

    public void setTotalHouseholds(int totalHouseholds) {
        this.totalHouseholds = totalHouseholds;
    }

    public int getTotalCattleHeadcount() {
        return totalCattleHeadcount;
    }

    public void setTotalCattleHeadcount(int totalCattleHeadcount) {
        this.totalCattleHeadcount = totalCattleHeadcount;
    }

    public int getTotalResidents() {
        return totalResidents;
    }

    public void setTotalResidents(int totalResidents) {
        this.totalResidents = totalResidents;
    }

    public double getTotalTaxesPaid() {
        return totalTaxesPaid;
    }

    public void setTotalTaxesPaid(double totalTaxesPaid) {
        this.totalTaxesPaid = totalTaxesPaid;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Household> getHouseholds() {
        return households;
    }

    public void setHouseholds(List<Household> households) {
        this.households = households;
    }
}
