package mk.ukim.finki.courses.model;

import jakarta.persistence.*;
import lombok.Data;

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

}
