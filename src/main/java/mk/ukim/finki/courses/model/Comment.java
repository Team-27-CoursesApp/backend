package mk.ukim.finki.courses.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2048)
    private String text;

    @ManyToOne
    private Course course;

    @ManyToOne
    private CourseUser user;

    public Comment(String text,Course course, CourseUser user){
        this.text = text;
        this.course = course;
        this.user = user;
    }
}
