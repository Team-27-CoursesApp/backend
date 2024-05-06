package mk.ukim.finki.courses.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 2048)
    private String description;
    private String lecturer;
    @ManyToOne
    private Category category;
    private String imageUrl;
    private String videoUrl;
    private float price;
    private float rating;

    public Course(String name,String description,  String lecturer,Category category,String imageUrl, String videoUrl, float price) {
        this.name = name;
        this.description = description;
        this.lecturer = lecturer;
        this.category=category;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.price = price;
        this.rating = 0;
    }
}
