package mk.ukim.finki.courses.model.DTO;
import lombok.Data;


@Data
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private String lecturer;
    private Long category;
    private String imageUrl;
    private String videoUrl;
    private float price;

    public CourseDto() {
    }

    public CourseDto(String name, String description, String lecturer, Long category, String imageUrl, String videoUrl, float price) {
        this.name = name;
        this.description = description;
        this.lecturer = lecturer;
        this.category = category;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.price = price;
    }
}
