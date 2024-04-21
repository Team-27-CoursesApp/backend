package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.enumerations.Role;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.service.CourseUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CourseUserServiceImpl implements CourseUserService, UserDetailsService {

    private final CourseUserRepository courseUserRepository;
    private final PasswordEncoder passwordEncoder;

    public CourseUserServiceImpl(CourseUserRepository courseUserRepository, PasswordEncoder passwordEncoder) {
        this.courseUserRepository = courseUserRepository;
        this.passwordEncoder = passwordEncoder;
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
}
