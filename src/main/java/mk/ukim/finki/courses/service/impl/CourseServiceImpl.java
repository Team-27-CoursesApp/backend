package mk.ukim.finki.courses.service.impl;

import jdk.jfr.Category;
import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.model.exceptions.LecturerNotFound;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.repository.LecturerRepository;
import mk.ukim.finki.courses.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return Optional.ofNullable(courseRepository.findById(id).orElseThrow(() -> new CourseNotFound(id)));
    }

    @Override
    public Optional<Course> saveCourse(String name, String description, Long lecturerId, List<Long> studentsId,CourseCategory category) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElseThrow(() -> new LecturerNotFound(lecturerId));

        List<CourseUser> studentsList = addStudents(studentsId);

        Course course = new Course(name, description, lecturer, studentsList,category);

        return Optional.of(courseRepository.save(course));
    }

    @Override
    public Optional<Course> updateCourse(Long id, String name, String description, Long lecturer, CourseCategory category) {
        Course c = courseRepository.findById(id).orElseThrow(() -> new CourseNotFound(id));
        Lecturer lecturer1=lecturerRepository.findById(lecturer).orElseThrow(() -> new LecturerNotFound(lecturer));

        c.setName(name);
        c.setDescription(description);
        c.setCategory(category);
        c.setLecturer(lecturer1);


        return Optional.of(courseRepository.save(c));
    }

    @Override
    public Optional<Course> addStudentsToCourse(Long courseId, List<Long> studentsId) {
        Course c = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));
        List<CourseUser> studentsList = addStudents(studentsId);
        c.setStudents(studentsList);
        return Optional.of(courseRepository.save(c));
    }

    private List<CourseUser> addStudents(List<Long> studentsId) {
        List<CourseUser> studentsList = new ArrayList<>();

        if (studentsId != null) {
            for (Long studentId : studentsId) {
                CourseUser student = courseUserRepository.findById(studentId).orElseThrow(() -> new CourseUserNotFound(studentId));
                studentsList.add(student);
            }
        }

        return studentsList;
    }

    @Override
    public Optional<Course> addStudentToCourse(Long courseId, Long studentId) {
        Course c = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));

        CourseUser student = courseUserRepository.findById(studentId).orElseThrow(() -> new CourseUserNotFound(studentId));

        List<CourseUser> students = c.getStudents();

        students.add(student);
        c.setStudents(students);

        return Optional.of(courseRepository.save(c));
    }


    @Override
    public Boolean deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFound(id));
        if (course == null) {
            return false;
        }
        courseRepository.delete(course);
        return true;
    }

    @Override
    public List<Course> searchCourses(String text) {
        return courseRepository.findAllByNameLikeOrDescriptionLike(text, text);
    }

    @Override
    public Page<Course> getCoursesFromPage(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public List<Course> findAllByCategory(CourseCategory category) {
        return courseRepository.findAllByCategory(category);
    }


}
