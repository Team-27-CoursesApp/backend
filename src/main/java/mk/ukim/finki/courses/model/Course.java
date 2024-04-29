package mk.ukim.finki.courses.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    CourseCategory category;

    public Course(String name, String description, Lecturer lecturer, List<CourseUser> students,CourseCategory category) {
        this.name = name;
        this.description = description;
        this.lecturer = lecturer;
        this.students = students;
        this.category=category;
    }
}
