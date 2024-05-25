package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Comment;
import mk.ukim.finki.courses.model.DTO.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment saveComment(CommentDto commentDto);
    List<CommentDto> getCommentsByCourse(Long courseId);
}
