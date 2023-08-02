package itmo.WesterosTax.model;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // Новое поле для хранения роли пользователя (LORD, LANDOWNER, COURIER)

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "landowner_id")
    private User landowner;

    @ManyToOne
    @JoinColumn(name = "lord_id")
    private User lord;

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setLord(User lord) {
        this.lord = lord;
    }

    public String getUsername() {
        return null;
    }

    public String getPassword() {
        return null;
    }
}
