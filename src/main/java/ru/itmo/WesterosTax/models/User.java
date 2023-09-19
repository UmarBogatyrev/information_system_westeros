package ru.itmo.WesterosTax.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    @Size(max = 20, message = "Логин должен быть от 1 до 20 символов")
    private String username;

    @NotBlank
    @Size(max = 120, min = 8, message = "Пароль должен должно быть от 8 до 120 символов")
    private String password;

    private boolean active;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "districtId")
    private District district;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "regionId")
    private Region region;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "landownerId")
    private User landowner;

    @ManyToOne
    @JoinColumn(name = "lordId")
    private User lord;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, boolean active, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public User getLandowner() {
        return landowner;
    }

    public void setLandowner(User landowner) {
        this.landowner = landowner;
    }

    public User getLord() {
        return lord;
    }

    public void setLord(User lord) {
        this.lord = lord;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}