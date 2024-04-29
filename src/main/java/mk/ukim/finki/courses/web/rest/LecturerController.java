package mk.ukim.finki.courses.web.rest;

import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.model.dto.LecturerDto;
import mk.ukim.finki.courses.service.CourseService;
import mk.ukim.finki.courses.service.LecturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Lecturer> addLecturer(@RequestBody LecturerDto lecturerDto){
        try{
            return this.lecturerService.createLecturer(lecturerDto)
                    .map(lecturer -> ResponseEntity.ok().body(lecturer))
                    .orElseGet(()->ResponseEntity.badRequest().build());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Lecturer> editLecturer(@PathVariable Long id, @RequestBody LecturerDto lecturerDto){
        try{
            return this.lecturerService.editLecturer(id,lecturerDto)
                    .map(lecturer -> ResponseEntity.ok().body(lecturer))
                    .orElseGet(()->ResponseEntity.badRequest().build());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/addCourse/{courseId}")
    public ResponseEntity<Lecturer> addCourseToLecturer(@PathVariable Long id,@PathVariable Long courseId){
        try{
            return this.lecturerService.addCourseToLecturer(id,courseId)
                    .map(lecturer -> ResponseEntity.ok().body(lecturer))
                    .orElseGet(()-> ResponseEntity.badRequest().build());
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<LecturerDto>> getAllLecturers() {
        try{
            return ResponseEntity.ok(lecturerService.getAllLecturers());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{lecturerId}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable Long lecturerId) {
        try{
            return this.lecturerService.getLecturerById(lecturerId)
                    .map(lecturer -> ResponseEntity.ok().body(lecturer))
                    .orElseGet(()->ResponseEntity.badRequest().build());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}