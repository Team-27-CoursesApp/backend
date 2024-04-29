package mk.ukim.finki.courses.model.dto;

import lombok.Data;

@Data
public class CourseUserCreationDto {
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;
}
