package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Lecturer;

import java.util.List;
import java.util.Optional;

public interface LecturerService {

    public List<Lecturer> getAllCourses();

    public Optional<Lecturer> getLecturerById(Long id);

    public Lecturer createLecturer(String fullname,String email,String description);

    public Lecturer editLecturer(Long id,String fullname, String email, String description);

    public void deleteLecturer(Long id);

    public List<Lecturer> findLecturerByName(String fullname);


}