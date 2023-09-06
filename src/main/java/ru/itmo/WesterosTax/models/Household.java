package ru.itmo.WesterosTax.models;

import javax.persistence.*;

@Entity
@Table(name = "households")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int residentCount;

    @Column(nullable = false)
    private double income;

    @Column(nullable = false)
    private double landArea;

    @Column(nullable = false)
    private int cattleHeadcount;

    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;

    @Column(nullable = false)
    private double taxesCollected;

    public Household() {
    }

    public Household(String name, String address, int residentCount, double income, double landArea, int cattleHeadcount, District district, double taxesCollected) {
        this.name = name;
        this.address = address;
        this.residentCount = residentCount;
        this.income = income;
        this.landArea = landArea;
        this.cattleHeadcount = cattleHeadcount;
        this.district = district;
        this.taxesCollected = taxesCollected;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getResidentCount() {
        return residentCount;
    }

    public void setResidentCount(int residentCount) {
        this.residentCount = residentCount;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getLandArea() {
        return landArea;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }

    public int getCattleHeadcount() {
        return cattleHeadcount;
    }

    public void setCattleHeadcount(int cattleHeadcount) {
        this.cattleHeadcount = cattleHeadcount;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public double getTaxesCollected() {
        return taxesCollected;
    }

    public void setTaxesCollected(double taxesCollected) {
        this.taxesCollected = taxesCollected;
    }
}
