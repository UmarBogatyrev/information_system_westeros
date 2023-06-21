package com.westerostax.westerostax.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lord_id")
    private Lord lord;

    // Конструкторы

    public User() {

    }

    public User(String name, String username, String password, District district, Region region, Lord lord) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.district = district;
        this.region = region;
        this.lord = lord;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public District getDistrictId() {
        return district;
    }

    public void setDistrictId(District district) {
        this.district = district;
    }

    public Region getRegionId() {
        return region;
    }

    public void setRegionId(Region region) {
        this.region = region;
    }

    public Lord getLordId() {
        return lord;
    }

    public void setLordId(Lord lord) {
        this.lord = lord;
    }
}
