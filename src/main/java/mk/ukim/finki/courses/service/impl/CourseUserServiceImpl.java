package mk.ukim.finki.courses.service.impl;
import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.DTO.PaginatedLecturersDto;
import mk.ukim.finki.courses.model.DTO.UserDto;
import mk.ukim.finki.courses.model.enumerations.Role;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.service.CourseUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseUserServiceImpl implements CourseUserService {

    private final CourseUserRepository courseUserRepository;
    private final CourseRepository courseRepository;

    public CourseUserServiceImpl(CourseUserRepository courseUserRepository,
                                 CourseRepository courseRepository,PasswordEncoder passwordEncoder,CourseUserRepository service) {
        this.courseUserRepository = courseUserRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public CourseUser create(CourseUser user) {
        return courseUserRepository.save(
                new CourseUser(
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getName(),
                        user.getSurname(),
                        user.getRole()
                        ));
    }

    @Override
    public UserDto findById(Long id) {
        CourseUser user = this.courseUserRepository.findById(id).orElseThrow(()->new CourseUserNotFound(id));
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getImageUrl(),
                user.getDescription()
        );
        return userDto;
    }

    @Override
    public UserDto findByUsername(String username) {
        CourseUser user = this.courseUserRepository.findByUsername(username).orElseThrow(()->new CourseUserNotFound());
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getImageUrl(),
                user.getDescription()
        );
        return userDto;
    }

    @Override
    public CourseUser edit(UserDto user) {
        CourseUser courseUser = this.courseUserRepository.findById(user.getId()).orElseThrow(() -> new CourseUserNotFound(user.getId()));
        courseUser.setDescription(user.getDescription());
        courseUser.setImageUrl(user.getImageUrl());
        return this.courseUserRepository.save(courseUser);
    }

    @Override
    public List<Course> getUserCourses(Long id) {
        return this.courseUserRepository.findById(id).get().getCourses();
    }

    @Override
    public PaginatedLecturersDto getLecturers(int page) {
        int pageSize = 12;
        List<CourseUser> lecturers =  this.courseUserRepository.findAllByRole("lecturer");
        List<UserDto> lecturersDto = lecturers.stream().skip((long) (page - 1) *pageSize).map(u -> new UserDto(
                u.getId(),
                u.getUsername(),
                u.getEmail(),
                u.getName(),
                u.getSurname(),
                u.getRole(),
                u.getImageUrl(),
                u.getDescription())).limit(pageSize).toList();
        int pages = lecturers.size() / pageSize;
        if((float)lecturers.size() / pageSize > pages){
            pages+=1;
        }
        return new PaginatedLecturersDto(page,pages,lecturersDto);
    }

    @Override
    public boolean HasBought(Long id, Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElseThrow(()->new CourseNotFound(courseId));
        List<Course> courses =  this.getUserCourses(id);
        return courses.contains(course);
    }

    @Override
    public CourseUser login(String email, String password) {
        return courseUserRepository.findByEmailAndPassword(email,password).orElseThrow(() -> new CourseUserNotFound());
    }
}
