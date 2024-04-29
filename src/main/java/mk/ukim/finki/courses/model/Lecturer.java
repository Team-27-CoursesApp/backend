package mk.ukim.finki.courses.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fullName;

    private String email;

    private String description;

    @OneToMany
    private List<Course> teaches;
    public Lecturer() {
    }
    public Lecturer(String fullName, String email, String description) {
        this.fullName = fullName;
        this.email = email;
        this.description = description;
    }
}