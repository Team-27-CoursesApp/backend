package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.dto.CourseDto;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.model.exceptions.LecturerNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseDto> getAllCourses();
    Optional<Course> getCourseById(Long id);
    Optional<Course> saveCourse(CourseDto courseDto) throws LecturerNotFound;
    Optional<Course> updateCourse(Long id, CourseDto courseDto) throws CourseNotFound,LecturerNotFound;
    Optional<Course> addStudentsToCourse(Long courseId, List<Long> studentsId) throws CourseNotFound;
    Optional<Course> addStudentToCourse(Long courseId, Long studentId) throws CourseNotFound,CourseUserNotFound ;
    Boolean deleteCourse(Long id);
    List<Course> searchCourses(String text);
    Page<Course> getCoursesFromPage(Pageable pageable);
    List<Course> findAllByCategory(CourseCategory category);
}
