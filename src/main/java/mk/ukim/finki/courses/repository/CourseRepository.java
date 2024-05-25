package mk.ukim.finki.courses.repository;
import mk.ukim.finki.courses.model.Category;
import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.enumerations.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
    List<Course> findByCategoryOrderByIdAsc(Category category);
    List<Course>findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
