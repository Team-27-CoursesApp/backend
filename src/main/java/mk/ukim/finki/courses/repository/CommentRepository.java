package mk.ukim.finki.courses.repository;

import mk.ukim.finki.courses.model.Comment;
import mk.ukim.finki.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCourse(Course course);
}
