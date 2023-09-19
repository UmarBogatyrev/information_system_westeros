package ru.itmo.WesterosTax.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class TaxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 50, message = "Название налога должно быть от 1 до 50 символов")
    private String name;

    @NotBlank
    @Size(min = 3, max = 25, message = "Формула налога должно быть от 3 до 25 символов")
    @Column(unique = true)
    private String formula;

    @NotBlank
    @Size(max = 1000, message = "Описание налога должно быть до 1000 символов")
    private String description;

    @ManyToOne
    @JoinColumn(name = "lordId")
    private User lord;

    public TaxType() {
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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getLord() {
        return lord;
    }

    public void setLord(User lord) {
        this.lord = lord;
    }
}
