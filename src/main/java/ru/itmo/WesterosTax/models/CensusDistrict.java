package ru.itmo.WesterosTax.models;

import javax.persistence.*;

@Entity
public class CensusDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int totalResidentCount;

    private double totalLandArea;

    private int totalCattleHeadcount;

    private String status;

    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;

    @ManyToOne
    @JoinColumn(name = "censusRegionId")
    private CensusRegion censusRegion;

    public CensusDistrict() {
    }

    public CensusDistrict(String status, District district, CensusRegion censusRegion) {
        this.status = status;
        this.district = district;
        this.censusRegion = censusRegion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotalResidentCount() {
        return totalResidentCount;
    }

    public void setTotalResidentCount(int totalResidentCount) {
        this.totalResidentCount = totalResidentCount;
    }

    public double getTotalLandArea() {
        return totalLandArea;
    }

    public void setTotalLandArea(double totalLandArea) {
        this.totalLandArea = totalLandArea;
    }

    public int getTotalCattleHeadcount() {
        return totalCattleHeadcount;
    }

    public void setTotalCattleHeadcount(int totalCattleHeadcount) {
        this.totalCattleHeadcount = totalCattleHeadcount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public CensusRegion getCensusRegion() {
        return censusRegion;
    }

    public void setCensusRegion(CensusRegion censusRegion) {
        this.censusRegion = censusRegion;
    }
}