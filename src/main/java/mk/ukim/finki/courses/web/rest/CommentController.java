package mk.ukim.finki.courses.web.rest;

import mk.ukim.finki.courses.model.Comment;
import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.DTO.CommentDto;
import mk.ukim.finki.courses.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://courses-app-umkt.onrender.com")
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{courseId}")
    public List<CommentDto> getCommentsByCourse(@PathVariable Long courseId) {
        return this.commentService.getCommentsByCourse(courseId);
    }

    @PostMapping
    public boolean saveComment(@RequestBody CommentDto commentDto){
        this.commentService.saveComment(commentDto);
        return true;
    }
}
