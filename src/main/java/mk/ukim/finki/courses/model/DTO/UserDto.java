package mk.ukim.finki.courses.model.DTO;

import lombok.Data;

@Data
public class UserDto {
    Long id;
    String username;
    String email;
    String name;
    String surname;
    String role;
    String imageUrl;
    String description;

    public UserDto() {
    }

    public UserDto(Long id, String username, String email, String name, String surname, String role, String imageUrl, String description) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
