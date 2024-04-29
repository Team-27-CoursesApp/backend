package mk.ukim.finki.courses.web.rest;


import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.dto.CourseDto;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import mk.ukim.finki.courses.service.CourseService;
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
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        try{
            return this.courseService.getCourseById(courseId)
                    .map(course -> ResponseEntity.ok().body(course))
                    .orElseGet(()->ResponseEntity.badRequest().build());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable Long courseId) {
        if(this.courseService.getCourseById(courseId).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
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

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto){
        try{
            return this.courseService.saveCourse(courseDto)
                    .map(course -> ResponseEntity.ok().body(course))
                    .orElseGet(()->ResponseEntity.badRequest().build());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto){
        try{
            return this.courseService.updateCourse(id,courseDto)
                    .map(course -> ResponseEntity.ok().body(course))
                    .orElseGet(()->ResponseEntity.badRequest().build());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<Course>> listByCategories(@PathVariable("category") CourseCategory category) {
        List<Course> courses = courseService.findAllByCategory(category);
        return ResponseEntity.ok(courses);
    }
}
