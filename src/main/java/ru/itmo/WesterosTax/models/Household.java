package ru.itmo.WesterosTax.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "households")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 50, message = "Название хозяйства должно быть от 1 до 50 символов")
    private String name;

    @NotNull
    private String address;

    @NotNull
    private int residentCount;

    @NotNull
    private double income;

    @NotNull
    private double landArea;

    @NotNull
    private int cattleHeadcount;

    @NotNull
    private double taxesCollected;

    @ManyToOne
    @JoinColumn(name = "courierId")
    private User courier;

    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;

    public Household() {
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

    public double getTaxesCollected() {
        return taxesCollected;
    }

    public void setTaxesCollected(double taxesCollected) {
        this.taxesCollected = taxesCollected;
    }

    public User getCourier() {
        return courier;
    }

    public void setCourier(User courier) {
        this.courier = courier;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}