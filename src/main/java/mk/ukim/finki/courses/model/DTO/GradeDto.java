package mk.ukim.finki.courses.model.DTO;

import lombok.Data;

@Data
public class GradeDto {
    private Long UserId;
    private Long CourseId;
    private int grade;

    public GradeDto() {
    }

    public GradeDto(Long userId, Long courseId, int grade) {
        UserId = userId;
        CourseId = courseId;
        this.grade = grade;
    }
}
