package mk.ukim.finki.courses.model.DTO;

import lombok.Data;

@Data
public class CreateCourseDto {
    private UserDto user;
    private CourseDto courseDto;

    public CreateCourseDto() {
    }

    public CreateCourseDto(UserDto user, CourseDto courseDto) {
        this.user = user;
        this.courseDto = courseDto;
    }
}
