package mk.ukim.finki.courses.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseUserDto {
    private String username;
    private String role;
    private Long shoppingCart;
    private List<Long> ownedCourses;
}
