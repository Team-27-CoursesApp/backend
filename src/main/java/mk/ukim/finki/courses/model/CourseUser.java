package mk.ukim.finki.courses.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.courses.model.enumerations.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CourseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String imageUrl;
    @Column(length = 2048)
    private String description;

    @ManyToMany
    private List<Course> courses;
    private String role;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;



    public CourseUser(String username, String email, String password, String name, String surname, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.imageUrl = "";
        this.description = "";
        this.courses = new ArrayList<>();
    }

    public CourseUser() {

    }
}
