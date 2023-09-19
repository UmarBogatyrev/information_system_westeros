package ru.itmo.WesterosTax.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class CensusRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int totalResidentCount;

    private double totalLandArea;

    private int totalCattleHeadcount;

    private String status;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "censusId")
    private Census census;

    public CensusRegion() {
    }

    public CensusRegion(String status, Region region, Census census) {
        this.status = status;
        this.region = region;
        this.census = census;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Census getCensus() {
        return census;
    }

    public void setCensus(Census census) {
        this.census = census;
    }
}
