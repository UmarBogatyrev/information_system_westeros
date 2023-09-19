package ru.itmo.WesterosTax.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class TaxDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double totalIncome;

    private String status;

    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;

    @ManyToOne
    @JoinColumn(name = "taxRegionId")
    private TaxRegion taxRegion;

    public TaxDistrict() {
    }

    public TaxDistrict(String status, District district, TaxRegion taxRegion) {
        this.status = status;
        this.district = district;
        this.taxRegion = taxRegion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
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

    public TaxRegion getTaxRegion() {
        return taxRegion;
    }

    public void setTaxRegion(TaxRegion taxRegion) {
        this.taxRegion = taxRegion;
    }
}