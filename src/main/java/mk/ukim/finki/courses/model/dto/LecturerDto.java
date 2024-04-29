package mk.ukim.finki.courses.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class LecturerDto {
    private String fullname;
    private String email;
    private String description;
    private List<Long> teaches;
}
