package mk.ukim.finki.courses.repository;

import mk.ukim.finki.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
