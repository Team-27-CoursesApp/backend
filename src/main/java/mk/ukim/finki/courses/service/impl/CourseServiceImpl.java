package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.model.dto.CourseDto;
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
    public List<CourseDto> getAllCourses() {
        List<Course> courses = this.courseRepository.findAll();
        List<CourseDto> coursesDto = new ArrayList<>();
        for(Course course : courses){
            CourseDto courseDto = new CourseDto();
            courseDto.setCategory(course.getCategory().toString());
            courseDto.setName(course.getName());
            courseDto.setDescription(course.getDescription());
            courseDto.setLecturer(course.getLecturer().getId());
            List<Long> studentIds = new ArrayList<>();
            for(CourseUser student : course.getStudents()){
                studentIds.add(student.getId());
            }
            courseDto.setStudents(studentIds);
            coursesDto.add(courseDto);
        }
        return coursesDto;
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return Optional.ofNullable(courseRepository.findById(id).orElseThrow(() -> new CourseNotFound(id)));
    }

    @Override
    public Optional<Course> saveCourse(CourseDto courseDto) throws LecturerNotFound {
        Lecturer lecturer = this.lecturerRepository.findById(courseDto.getLecturer()).orElseThrow(()-> new LecturerNotFound(courseDto.getLecturer()));
        List<CourseUser> studentsList = addStudents(courseDto.getStudents());
        Course course = new Course(
                courseDto.getName(),
                courseDto.getDescription(),
                lecturer,
                studentsList,
                CourseCategory.valueOf(courseDto.getCategory())
        );
        return Optional.of(this.courseRepository.save(course));
    }

    @Override
    public Optional<Course> updateCourse(Long id, CourseDto courseDto) throws CourseNotFound,LecturerNotFound {
        Course course = this.courseRepository.findById(id).orElseThrow(()->new CourseNotFound(id));
        Lecturer lecturer = this.lecturerRepository.findById(courseDto.getLecturer()).orElseThrow(()-> new LecturerNotFound(courseDto.getLecturer()));
        List<CourseUser> studentsList = addStudents(courseDto.getStudents());

        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setLecturer(lecturer);
        course.setStudents(studentsList);
        course.setCategory(CourseCategory.valueOf(courseDto.getCategory()));

        return Optional.of(this.courseRepository.save(course));
    }

    @Override
    public Optional<Course> addStudentsToCourse(Long courseId, List<Long> studentsId) throws CourseNotFound{
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
    public Optional<Course> addStudentToCourse(Long courseId, Long studentId) throws CourseNotFound,CourseUserNotFound {
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
