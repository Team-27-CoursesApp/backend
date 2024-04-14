package mk.ukim.finki.courses.repository;

import mk.ukim.finki.courses.model.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser, Long> {
}
