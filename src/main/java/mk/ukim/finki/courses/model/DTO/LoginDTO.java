package mk.ukim.finki.courses.model.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    String email;
    String password;

    public LoginDTO(){

    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
