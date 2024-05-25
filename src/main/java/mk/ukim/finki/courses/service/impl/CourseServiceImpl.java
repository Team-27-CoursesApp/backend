package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Category;
import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.DTO.CourseDto;
import mk.ukim.finki.courses.model.DTO.PaginatedCourseDto;
import mk.ukim.finki.courses.model.DTO.UserDto;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import mk.ukim.finki.courses.model.exceptions.CategoryNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.model.exceptions.LecturerNotFound;
import mk.ukim.finki.courses.repository.CategoryRepository;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseUserRepository courseUserRepository;
    private final CategoryRepository categoryRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseUserRepository courseUserRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.courseUserRepository = courseUserRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return this.courseRepository.findById(id);
    }

    @Override
    public Optional<Course> saveCourse(UserDto user, CourseDto course) {
        Category category = this.categoryRepository.findById(course.getCategory()).orElseThrow(()->new CourseUserNotFound());
        Course c = this.courseRepository.save(new Course(
                course.getName(),
                course.getDescription(),
                course.getLecturer(),
                category,
                course.getImageUrl(),
                course.getVideoUrl(),
                course.getPrice()));
        this.addStudentToCourse(c.getId(),user.getId());
        return Optional.of(c);
    }


    @Override
    public CourseUser addStudentToCourse(Long courseId, Long userId) {
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));
        CourseUser courseUser = this.courseUserRepository.findById(userId).orElseThrow(() -> new CourseUserNotFound(userId));
        courseUser.getCourses().add(course);
        return this.courseUserRepository.save(courseUser);
    }

    @Override
    public Boolean deleteCourse(Long id) {
        return null;
    }

    @Override
    public List<Course> searchCourses(String text) {
        return this.courseRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text,text).stream().limit(5).collect(Collectors.toList());

    }

    @Override
    public PaginatedCourseDto findByCategory(Long categoryId, int page) {
        int pageSize = 12;
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFound(categoryId));
        List<Course>allCourses = this.courseRepository.findByCategoryOrderByIdAsc(category);
        List<Course>currentPage = allCourses.stream().skip((long) (page - 1) * pageSize).limit(pageSize).toList();
        int pages = allCourses.size() / pageSize;
        if((float)allCourses.size() / pageSize > pages){
            pages+=1;
        }
        return new PaginatedCourseDto(page,pages,currentPage);
    }

}
