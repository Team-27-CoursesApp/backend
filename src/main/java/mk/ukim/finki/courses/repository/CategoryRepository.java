package mk.ukim.finki.courses.repository;

import mk.ukim.finki.courses.model.Category;
import mk.ukim.finki.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findBySlug(String slug);

}
