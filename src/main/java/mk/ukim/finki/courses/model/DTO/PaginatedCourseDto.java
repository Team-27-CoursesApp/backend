package mk.ukim.finki.courses.model.DTO;

import lombok.Data;
import mk.ukim.finki.courses.model.Course;

import java.util.List;

@Data
public class PaginatedCourseDto {
    int page;
    int pages;
    List<Course>courses;

    public PaginatedCourseDto() {
    }

    public PaginatedCourseDto(int page, int pages, List<Course> courses) {
        this.page = page;
        this.pages = pages;
        this.courses = courses;
    }
}
