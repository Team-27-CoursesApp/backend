package mk.ukim.finki.courses.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    String name;
    String description;

    @ManyToOne
    private Lecturer lecturer;

    @ManyToMany
    private List<CourseUser> students;
}
