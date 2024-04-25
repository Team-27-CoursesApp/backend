package mk.ukim.finki.courses.web.rest;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.service.CourseService;
import mk.ukim.finki.courses.service.LecturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lecturers")
public class LecturerController {

    private final CourseService courseService;

    private final LecturerService lecturerService;

    public LecturerController(CourseService courseService, LecturerService lecturerService) {
        this.courseService = courseService;
        this.lecturerService = lecturerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Lecturer> addLecturer(@RequestParam String fullName,
                                                @RequestParam String email,
                                                @RequestParam String description
                                               ) {
        Optional<Lecturer> lecturerOptional = lecturerService.createLecturer(fullName, email, description);
        if (lecturerOptional.isPresent()) {
            return ResponseEntity.ok(lecturerOptional.get());
        } else {
            // Handle the case where the Optional is empty, e.g., return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lecturer>> getAllLecturers() {
        return ResponseEntity.ok(lecturerService.getAllLecturers());
    }

    @GetMapping("/{lecturerId}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable Long lecturerId) {
        return ResponseEntity.ok(lecturerService.getLecturerById(lecturerId).get());
    }

}
