package ru.itmo.WesterosTax.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private int totalHouseholds;

    private int totalCattleHeadcount;

    private int totalResidents;

    private double totalTaxesPaid;

    @OneToMany(mappedBy = "region")
    private List<District> districts;

    public Region() {
    }

    public Region(String name, int totalHouseholds, int totalCattleHeadcount, int totalResidents, double totalTaxesPaid) {
        this.name = name;
        this.totalHouseholds = totalHouseholds;
        this.totalCattleHeadcount = totalCattleHeadcount;
        this.totalResidents = totalResidents;
        this.totalTaxesPaid = totalTaxesPaid;
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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
