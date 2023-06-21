package com.westerostax.westerostax.models;

import javax.persistence.*;

@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "taxes_collected", nullable = false)
    private Double taxesCollected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    // Конструкторы

    public District() {

    }

    public District(String name, Double taxesCollected, Region region) {
        this.name = name;
        this.taxesCollected = taxesCollected;
        this.region = region;
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

    public Double getTaxesCollected() {
        return taxesCollected;
    }

    public void setTaxesCollected(Double taxesCollected) {
        this.taxesCollected = taxesCollected;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
