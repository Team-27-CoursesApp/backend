package mk.ukim.finki.courses.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private CourseUser user;

    private int grade;

    public Grade(Course course, CourseUser user, int grade) {
        this.course = course;
        this.user = user;
        this.grade = grade;
    }
}
