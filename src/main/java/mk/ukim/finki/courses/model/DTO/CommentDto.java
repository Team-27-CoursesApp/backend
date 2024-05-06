package mk.ukim.finki.courses.model.DTO;

import lombok.Data;

@Data
public class CommentDto {
    private String username;
    private String profileImg;
    private String text;
    private Long userId;
    private Long courseId;

    public CommentDto() {
    }

    public CommentDto(String username, String profileImg, String text, Long userId, Long courseId) {
        this.username = username;
        this.profileImg = profileImg;
        this.text = text;
        this.userId = userId;
        this.courseId = courseId;
    }
}
