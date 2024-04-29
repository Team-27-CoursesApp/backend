package mk.ukim.finki.courses.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private String name;
    private String description;
    private Long lecturer;
    private List<Long> students;
    private String category;
}
