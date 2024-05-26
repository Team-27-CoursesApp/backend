package mk.ukim.finki.courses.web.rest;


import mk.ukim.finki.courses.model.DTO.CommentDto;
import mk.ukim.finki.courses.model.DTO.GradeDto;
import mk.ukim.finki.courses.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://courses-app-umkt.onrender.com")
@RequestMapping("/api/grade")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public int getGrade(@RequestParam Long courseId, @RequestParam Long userId) {
        return this.gradeService.checkForGrade(courseId,userId);
    }

    @PostMapping
    public boolean saveGrade(@RequestBody GradeDto gradeDto){
        return this.gradeService.saveGrade(gradeDto);
    }
}
