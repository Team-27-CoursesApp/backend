package mk.ukim.finki.courses.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.courses.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    private List<Course> courses;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    private LocalDateTime dateCreated;

    @OneToOne
    private CourseUser user;

    private Double totalPrice;
}
