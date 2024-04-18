package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.model.exceptions.LecturerNotFound;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.LecturerRepository;
import mk.ukim.finki.courses.service.LecturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository, CourseRepository courseRepository) {
        this.lecturerRepository = lecturerRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Lecturer> getAllCourses() {
        return lecturerRepository.findAll();
    }

    @Override
    public Optional<Lecturer> getLecturerById(Long id) {
        Lecturer lecturer=lecturerRepository.findById(id).orElseThrow(() -> new LecturerNotFound(id));
        return Optional.of(lecturer);
    }

    @Override
    public Lecturer createLecturer(String fullname, String email, String description) {
        Lecturer lecturer=new Lecturer(fullname,email,description);

        return lecturerRepository.save(lecturer);
    }

    @Override
    public Lecturer editLecturer(Long id, String fullname, String email, String description) {
        Lecturer lecturer=lecturerRepository.findById(id).orElseThrow(() -> new LecturerNotFound(id));
        lecturer.setDescription(description);
        lecturer.setEmail(email);
        lecturer.setFullName(fullname);
        return lecturerRepository.save(lecturer);
    }

    @Override
    public void deleteLecturer(Long id) {
        Lecturer lecturer=lecturerRepository.findById(id).orElseThrow(() -> new LecturerNotFound(id));
        lecturerRepository.delete(lecturer);

    }

    @Override
    public List<Lecturer> findLecturerByName(String fullname) {

        return lecturerRepository.findAllByFullNameContaining(fullname);
    }
}