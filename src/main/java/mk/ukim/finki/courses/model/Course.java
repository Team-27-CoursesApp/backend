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

    private String name;

    private String description;

    private Double price;

    @ManyToOne
    private Lecturer lecturer;

    @ManyToMany
    private List<CourseUser> students;

    @Enumerated(EnumType.STRING)
    CourseCategory category;

    public Course(String name, String description, Double price, Lecturer lecturer, List<CourseUser> students,CourseCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.lecturer = lecturer;
        this.students = students;
        this.category=category;
    }
}
