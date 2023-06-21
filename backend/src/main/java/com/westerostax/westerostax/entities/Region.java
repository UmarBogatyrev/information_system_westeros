package com.westerostax.westerostax.models;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "total_households")
    private Integer totalHouseholds;

    @Column(name = "total_cattle_headcount")
    private Integer totalCattleHeadcount;

    @Column(name = "total_residents")
    private Integer totalResidents;

    @Column(name = "total_taxes_paid")
    private Double totalTaxesPaid;

    // конструкторы
    public Region() {
    }

    public Region(String name, Integer totalHouseholds, Integer totalCattleHeadcount, Integer totalResidents,
            Double totalTaxesPaid) {
        this.name = name;
        this.totalHouseholds = totalHouseholds;
        this.totalCattleHeadcount = totalCattleHeadcount;
        this.totalResidents = totalResidents;
        this.totalTaxesPaid = totalTaxesPaid;
    }

    // гетеры и сеттеры чуть что
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalHouseholds() {
        return totalHouseholds;
    }

    public void setTotalHouseholds(Integer totalHouseholds) {
        this.totalHouseholds = totalHouseholds;
    }

    public Integer getTotalCattleHeadcount() {
        return totalCattleHeadcount;
    }

    public void setTotalCattleHeadcount(Integer totalCattleHeadcount) {
        this.totalCattleHeadcount = totalCattleHeadcount;
    }

    public Integer getTotalResidents() {
        return totalResidents;
    }

    public void setTotalResidents(Integer totalResidents) {
        this.totalResidents = totalResidents;
    }

    public Double getTotalTaxesPaid() {
        return totalTaxesPaid;
    }

    public void setTotalTaxesPaid(Double totalTaxesPaid) {
        this.totalTaxesPaid = totalTaxesPaid;
    }
}
