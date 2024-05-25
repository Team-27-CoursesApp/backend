package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Comment;
import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.DTO.CommentDto;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.repository.CommentRepository;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.service.CommentService;
import mk.ukim.finki.courses.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CourseRepository courseRepository;
    private final CourseUserRepository courseUserRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CourseRepository courseRepository, CourseUserRepository courseUserRepository, CommentRepository commentRepository) {
        this.courseRepository = courseRepository;
        this.courseUserRepository = courseUserRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment saveComment(CommentDto commentDto) {
        Course course = this.courseRepository.findById(commentDto.getCourseId()).orElseThrow(()->new CourseNotFound(commentDto.getCourseId()));
        CourseUser user = this.courseUserRepository.findById(commentDto.getUserId()).orElseThrow(()->new CourseUserNotFound(commentDto.getUserId()));
        return this.commentRepository.save(new Comment(commentDto.getText(),course,user));
    }

    @Override
    public List<CommentDto> getCommentsByCourse(Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));
        List<Comment> comments = this.commentRepository.findByCourse(course);
        List<CommentDto>commentDtos = comments.stream().map(c -> new CommentDto(c.getUser().getUsername(),c.getUser().getImageUrl(),c.getText(),c.getUser().getId(),c.getCourse().getId())).toList();
        return commentDtos;
    }
}
