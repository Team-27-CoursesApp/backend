package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.dto.CourseUserCreationDto;
import mk.ukim.finki.courses.model.exceptions.UserAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface CourseUserService {
    Optional<CourseUser> create(CourseUserCreationDto userCreationDto) throws UserAlreadyExistsException;
    List<Course> getUserCourses(String username);
}
