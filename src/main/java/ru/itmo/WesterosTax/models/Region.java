package ru.itmo.WesterosTax.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 50, message = "Название региона должно быть от 1 до 50 символов")
    @Column(unique = true)
    private String name;

    private int totalHouseholds;

    private int totalCattleHeadcount;

    private int totalResidents;

    private double totalTaxesPaid;

    @ManyToOne
    @JoinColumn(name = "lordId")
    private User lord;

    @OneToMany(mappedBy = "region")
    private List<District> districts;

    public Region() {
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

    public User getLord() {
        return lord;
    }

    public void setLord(User lord) {
        this.lord = lord;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
