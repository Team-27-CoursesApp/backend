package mk.ukim.finki.courses.repository;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser, Long> {
    List<CourseUser> findAllByRole(String role);
    Optional<CourseUser> findByUsername(String username);
    Optional<CourseUser> findByEmailAndPassword(String email, String password);



}
