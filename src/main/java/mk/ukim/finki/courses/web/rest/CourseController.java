package mk.ukim.finki.courses.web.rest;


import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.service.CourseService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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


    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId).get());
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.deleteCourse(courseId));
    }

    // pagination
    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<Page<Course>> getCoursesPage(@PathVariable int page, @PathVariable int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(courseService.getCoursesFromPage(pageRequest));
    }

    @GetMapping("/search/{text}")
    public ResponseEntity<List<Course>> searchCourses(@PathVariable String text) {
        return ResponseEntity.ok(courseService.searchCourses(text));
    }

    @PostMapping("/addStudent/{courseId}/{studentId}")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.addStudentToCourse(courseId, studentId).get());
    }

    @PostMapping("/addStudents/{courseId}/{students}")
    public ResponseEntity<Course> addStudentsToCourse(@PathVariable Long courseId, @PathVariable List<Long> students) {
        return ResponseEntity.ok(courseService.addStudentsToCourse(courseId, students).get());
    }


    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestParam String name,
                                             @RequestParam String description,
                                             @RequestParam Long lecturer,
                                             @RequestParam(required = false) List<Long> students) {
        return ResponseEntity.ok(courseService.saveCourse(name, description, lecturer, students).get());
    }

    @PostMapping("/update/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId,
                                               @RequestParam String name,
                                               @RequestParam String description,
                                               @RequestParam Long lecturerId) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, name, description, lecturerId).get());
    }

}
