package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.DTO.PaginatedLecturersDto;
import mk.ukim.finki.courses.model.DTO.UserDto;
import mk.ukim.finki.courses.model.enumerations.Role;


import java.util.List;
import java.util.Optional;

public interface CourseUserService {
    CourseUser create(CourseUser user);
    UserDto findById(Long id);
    UserDto findByUsername(String username);
    CourseUser edit(UserDto user);
    List<Course> getUserCourses(Long id);

    PaginatedLecturersDto getLecturers(int page);

    boolean HasBought(Long id, Long courseId);
    CourseUser login(String email, String password);
}
