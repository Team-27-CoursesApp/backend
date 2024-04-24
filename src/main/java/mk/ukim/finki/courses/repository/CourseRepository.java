package mk.ukim.finki.courses.repository;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByNameLikeOrDescriptionLike(String name, String description);
    List<Course> findAllByCategory(CourseCategory category);
}
