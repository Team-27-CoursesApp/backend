package mk.ukim.finki.courses.repository;

import mk.ukim.finki.courses.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    public List<Lecturer> findAllByFullNameContaining(String name);
}