package mk.ukim.finki.courses.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    String name;
    String description;

}
