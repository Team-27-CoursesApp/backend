package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourses();

    Optional<Course> getCourseById(Long id);

    Optional<Course> saveCourse(String name, String description, Long lecturer, List<Long> students, CourseCategory category);

    Optional<Course> updateCourse(Long id, String name, String description, Long lecturer,CourseCategory category);

    Optional<Course> addStudentsToCourse(Long courseId, List<Long> studentsId);
    Optional<Course> addStudentToCourse(Long courseId, Long studentId);

    Boolean deleteCourse(Long id);

    List<Course> searchCourses(String text);

    Page<Course> getCoursesFromPage(Pageable pageable);

    List<Course> findAllByCategory(CourseCategory category);


}
