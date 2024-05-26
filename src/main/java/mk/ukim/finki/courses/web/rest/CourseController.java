package mk.ukim.finki.courses.web.rest;


import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.DTO.*;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.service.CourseService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://courses-app-umkt.onrender.com")
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;



    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<PaginatedCourseDto> getCourseByCategory(@PathVariable Long id, @RequestParam int page){
        return ResponseEntity.ok(courseService.findByCategory(id,page));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId).get());
    }

    @PostMapping
    public Course create(@RequestBody CreateCourseDto createCourseDto){
        return this.courseService.saveCourse(createCourseDto.getUser(),createCourseDto.getCourseDto()).orElseThrow(() -> new CourseUserNotFound());
    }

    @PostMapping("/payment")
    public boolean addCoursesToStudent(@RequestBody CartDto cartDto){
        cartDto.getCourses().forEach(course -> this.courseService.addStudentToCourse(course.getId(), cartDto.getUser().getId()));
        return true;
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.deleteCourse(courseId));
    }






    @GetMapping("/search/{text}")
    public List<Course> searchCourses(@PathVariable String text) {
        return courseService.searchCourses(text);
    }

    //    // pagination
//    @GetMapping("/page/{page}/size/{size}")
//    public ResponseEntity<Page<Course>> getCoursesPage(@PathVariable int page, @PathVariable int size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        return ResponseEntity.ok(courseService.getCoursesFromPage(pageRequest));
//    }

//    @PostMapping("/addStudent/{courseId}/{studentId}")
//    public ResponseEntity<Course> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
//        return ResponseEntity.ok(courseService.addStudentToCourse(courseId, studentId).get());
//    }

//    @PostMapping("/addStudents/{courseId}/{students}")
//    public ResponseEntity<Course> addStudentsToCourse(@PathVariable Long courseId, @PathVariable List<Long> students) {
//        return ResponseEntity.ok(courseService.addStudentsToCourse(courseId, students).get());
//    }


//    @PostMapping("/add")
//    public ResponseEntity<Course> addCourse(@RequestParam String name,
//                                             @RequestParam String description,
//                                             @RequestParam Long lecturer,
//                                             @RequestParam(required = false) List<Long> students,
//                                            @RequestParam CourseCategory category) {
//        return ResponseEntity.ok(courseService.saveCourse(name, description, lecturer, students,category).get());
//    }

//    @PostMapping("/update/{courseId}")
//    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId,
//                                               @RequestParam String name,
//                                               @RequestParam String description,
//                                               @RequestParam Long lecturerId,
//                                            @RequestParam CourseCategory category) {
//        return ResponseEntity.ok(courseService.updateCourse(courseId, name, description, lecturerId,category).get());
//    }
//    @GetMapping("/categories/{category}")
//    public ResponseEntity<List<Course>> listByCategories(@PathVariable("category") CourseCategory category) {
//        List<Course> courses = courseService.findAllByCategory(category);
//        return ResponseEntity.ok(courses);
//    }
}
