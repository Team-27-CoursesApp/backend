package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.Lecturer;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<Course> getAllCourses();

    public Optional<Course> getCourseById(Long id);

    public Optional<Course> saveCourse(String name, String description, Long lecturer, List<Long> students);

    public Optional<Course> updateCourse(Long id, String name, String description, Long lecturer, List<CourseUser> students);

    public void deleteCourse(Long id);

    public List<Course> searchCourses(String text);


}
