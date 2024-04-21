package mk.ukim.finki.courses.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.courses.model.enumerations.Role;

import java.util.List;

@Entity
@Data
public class CourseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String email;
    private String password;

    private String name;
    private String surname;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @OneToOne
    private ShoppingCart shoppingCarts;

    @ManyToMany
    private List<Course> ownedCourses;

    @Enumerated(EnumType.STRING)
    private Role role;

    public CourseUser(String username, String email, String password, String name, String surname, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public CourseUser() {

    }
}
