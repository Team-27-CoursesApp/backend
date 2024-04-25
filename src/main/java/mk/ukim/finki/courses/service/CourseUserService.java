package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.enumerations.Role;

import java.util.List;

public interface CourseUserService {
    CourseUser create(String username, String email, String password, String name, String surname, Role role);

    List<Course> getUserCourses(String username);
}
