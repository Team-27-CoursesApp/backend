package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.model.exceptions.LecturerNotFound;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.repository.LecturerRepository;
import mk.ukim.finki.courses.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final LecturerRepository lecturerRepository;

    private final CourseUserRepository courseUserRepository;

    public CourseServiceImpl(CourseRepository courseRepository, LecturerRepository lecturerRepository, CourseUserRepository courseUserRepository) {
        this.courseRepository = courseRepository;
        this.lecturerRepository = lecturerRepository;
        this.courseUserRepository = courseUserRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e) {
            throw new CourseNotFound(id);
        }
    }

    @Override
    public Optional<Course> saveCourse(String name, String description, Long lecturerId, List<Long> students) {

        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElseThrow(() -> new LecturerNotFound(lecturerId));

        List<CourseUser> studentsList = new ArrayList<>();

        for (Long studentId : students) {
            CourseUser student = courseUserRepository.findById(studentId).orElseThrow(() -> new CourseUserNotFound(studentId));
        }

        Course course = new Course(name, description, lecturer, studentsList);

        return Optional.of(courseRepository.save(course));
    }

    @Override
    public Optional<Course> updateCourse(Long id, String name, String description, Long lecturer, List<CourseUser> students) {
        Course c = courseRepository.findById(id).orElseThrow(() -> new CourseNotFound(id));
//
//        c.setName(course.getName());
//        c.setDescription(course.getDescription());
//        c.setStudents(course.getStudents());


        return Optional.of(courseRepository.save(c));
    }

    @Override
    public void deleteCourse(Long id) {

    }

    @Override
    public List<Course> searchCourses(String text) {
        return null;
    }
}
