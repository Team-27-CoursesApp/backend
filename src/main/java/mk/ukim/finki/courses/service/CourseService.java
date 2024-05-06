package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Category;
import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.DTO.CourseDto;
import mk.ukim.finki.courses.model.DTO.PaginatedCourseDto;
import mk.ukim.finki.courses.model.DTO.UserDto;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourses();

    Optional<Course> getCourseById(Long id);

    Optional<Course> saveCourse(UserDto user,CourseDto course);
    CourseUser addStudentToCourse(Long courseId, Long userId);
    Boolean deleteCourse(Long id);
    List<Course> searchCourses(String text);
    PaginatedCourseDto findByCategory(Long categoryId, int page);

}
