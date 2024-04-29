package mk.ukim.finki.courses.web.rest;

import mk.ukim.finki.courses.model.Course;

import mk.ukim.finki.courses.service.CourseService;
import mk.ukim.finki.courses.service.CourseUserService;
import mk.ukim.finki.courses.service.LecturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class CourseUserController {

    private final CourseUserService courseUserService;

    private final CourseService courseService;

    private final LecturerService lecturerService;

    public CourseUserController(CourseUserService courseUserService, CourseService courseService, LecturerService lecturerService) {
        this.courseUserService = courseUserService;
        this.courseService = courseService;
        this.lecturerService = lecturerService;
    }

    @GetMapping("/{username}/myCourses")
    public ResponseEntity<List<Course>> getAllMyCourses(@PathVariable String username) {
        return ResponseEntity.ok(courseUserService.getUserCourses(username));
    }
}