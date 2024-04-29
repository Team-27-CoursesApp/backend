package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.model.dto.LecturerDto;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.LecturerNotFound;

import java.util.List;
import java.util.Optional;

public interface LecturerService {
    public List<LecturerDto> getAllLecturers();
    public Optional<Lecturer> getLecturerById(Long id);
    public Optional<Lecturer> createLecturer(LecturerDto lecturerDto) throws LecturerNotFound;
    public Optional<Lecturer> editLecturer(Long id,LecturerDto lecturerDto) throws LecturerNotFound;
    public void deleteLecturer(Long id);
    public List<Lecturer> findLecturerByName(String fullname);
    public List<Course> getTeaches(Long id) throws LecturerNotFound;
    public Optional<Lecturer> addCourseToLecturer(Long id, Long courseId) throws LecturerNotFound, CourseNotFound;
}