package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.enumerations.Role;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.service.CourseUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CourseUserServiceImpl implements CourseUserService, UserDetailsService {

    private final CourseUserRepository courseUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final CourseUserRepository service;

    private final CourseRepository courseRepository;

    public CourseUserServiceImpl(CourseUserRepository courseUserRepository,
                                 CourseRepository courseRepository,PasswordEncoder passwordEncoder,CourseUserRepository service) {
        this.courseUserRepository = courseUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.service=service;
        this.courseRepository=courseRepository;
    }

    @Override
    public CourseUser create(String username, String email, String password, String name, String surname, Role role) {
        String encodedPassword = passwordEncoder.encode(password);
        return courseUserRepository.save(new CourseUser(username, email, encodedPassword, name, surname, role));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CourseUser courseUser = courseUserRepository.findByUsername(username).orElseThrow(CourseUserNotFound::new);
        return new User(courseUser.getUsername(), courseUser.getPassword(), Collections.singletonList(courseUser.getRole()));
    }

    @Override
    public List<Course> getUserCourses(String username) {
        CourseUser user=courseUserRepository.findByUsername(username).orElseThrow(CourseUserNotFound::new);
        List<Course> myCourses=user.getOwnedCourses();

        return myCourses;
    }

}
