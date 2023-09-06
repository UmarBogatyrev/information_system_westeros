package ru.itmo.WesterosTax.models;

import javax.persistence.*;

@Entity
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    @JoinColumn(name = "regionId")
    private Region region;

    public District() {
    }

    public District(String name, int totalHouseholds, int totalCattleHeadcount, int totalResidents, double totalTaxesPaid, Region region) {
        this.name = name;
        this.totalHouseholds = totalHouseholds;
        this.totalCattleHeadcount = totalCattleHeadcount;
        this.totalResidents = totalResidents;
        this.totalTaxesPaid = totalTaxesPaid;
        this.region = region;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
