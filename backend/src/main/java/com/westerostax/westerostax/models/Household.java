package com.westerostax.westerostax.models;

import javax.persistence.*;

@Entity
@Table(name = "households")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "resident_count", nullable = false)
    private Integer residentCount;

    @Column(name = "income", nullable = false)
    private Double income;

    @Column(name = "land_area", nullable = false)
    private Double landArea;

    @Column(name = "cattle_headcount", nullable = false)
    private Integer cattleHeadcount;

    @Column(name = "taxes_collected", nullable = false)
    private Double taxesCollected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    // Конструкторы

    public Household() {

    }

    public Household(String name, String address, Integer residentCount, Double income, Double landArea,
            Integer cattleHeadcount, District district, Double taxesCollected) {
        this.name = name;
        this.address = address;
        this.residentCount = residentCount;
        this.income = income;
        this.landArea = landArea;
        this.cattleHeadcount = cattleHeadcount;
        this.district = district;
        this.taxesCollected = taxesCollected;
    }

    // Геттеры и сеттеры

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getResidentCount() {
        return residentCount;
    }

    public void setResidentCount(Integer residentCount) {
        this.residentCount = residentCount;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getLandArea() {
        return landArea;
    }

    public void setLandArea(Double landArea) {
        this.landArea = landArea;
    }

    public Integer getCattleHeadcount() {
        return cattleHeadcount;
    }

    public void setCattleHeadcount(Integer cattleHeadcount) {
        this.cattleHeadcount = cattleHeadcount;
    }

    public District getDistrictId() {
        return district;
    }

    public void setDistrictId(District district) {
        this.district = district;
    }

    public Double getTaxesCollected() {
        return taxesCollected;
    }

    public void setTaxesCollected(Double taxesCollected) {
        this.taxesCollected = taxesCollected;
    }
}
