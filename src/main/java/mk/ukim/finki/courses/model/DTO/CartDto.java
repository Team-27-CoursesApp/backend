package mk.ukim.finki.courses.model.DTO;

import lombok.Data;
import mk.ukim.finki.courses.model.Course;

import java.util.List;

@Data
public class CartDto {
    private UserDto user;
    private List<Course> courses;

    public CartDto() {
    }

    public CartDto(UserDto user, List<Course> courses) {
        this.user = user;
        this.courses = courses;
    }
}
