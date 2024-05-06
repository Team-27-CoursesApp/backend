package mk.ukim.finki.courses.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedLecturersDto {
    int page;
    int pages;
    List<UserDto>lecturers;

    public PaginatedLecturersDto() {
    }

    public PaginatedLecturersDto(int page, int pages, List<UserDto> lecturers) {
        this.page = page;
        this.pages = pages;
        this.lecturers = lecturers;
    }
}
