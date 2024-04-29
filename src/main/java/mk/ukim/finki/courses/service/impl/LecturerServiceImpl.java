package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.Lecturer;
import mk.ukim.finki.courses.model.dto.LecturerDto;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.LecturerAlreadyExistsException;
import mk.ukim.finki.courses.model.exceptions.LecturerNotFound;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.LecturerRepository;
import mk.ukim.finki.courses.service.LecturerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<LecturerDto> getAllLecturers() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        List<LecturerDto> lecturersDto = new ArrayList<>();
        for(Lecturer lecturer : lecturers){
            LecturerDto lecturerDto = new LecturerDto();
            lecturerDto.setFullname(lecturer.getFullName());
            lecturerDto.setDescription(lecturer.getDescription());
            lecturerDto.setEmail(lecturer.getEmail());
            List<Long> courseIds = new ArrayList<>();
            for(Course course : lecturer.getTeaches()){
                courseIds.add(course.getId());
            }
            lecturerDto.setTeaches(courseIds);
            lecturersDto.add(lecturerDto);
        }
        return lecturersDto;
    }

    @Override
    public Optional<Lecturer> getLecturerById(Long id) {
        Lecturer lecturer = lecturerRepository.findById(id).orElseThrow(() -> new LecturerNotFound(id));
        return Optional.of(lecturer);
    }

    @Override
    public Optional<Lecturer> createLecturer(LecturerDto lecturerDto) throws LecturerAlreadyExistsException {
        if(!this.lecturerRepository.findAllByFullNameContaining(lecturerDto.getFullname()).isEmpty()){
            throw new LecturerAlreadyExistsException();
        }
        List<Course> teaches = this.courseRepository.findAllById(lecturerDto.getTeaches());

        Lecturer lecturer = new Lecturer(
                lecturerDto.getFullname(),
                lecturerDto.getEmail(),
                lecturerDto.getDescription(),
                teaches
                );
        return Optional.of(this.lecturerRepository.save(lecturer));
    }

    @Override
    public Optional<Lecturer> editLecturer(Long id, LecturerDto lecturerDto) throws LecturerNotFound {
        Lecturer lecturer = this.lecturerRepository.findById(id).orElseThrow(()-> new LecturerNotFound(id));
        List<Course> courses = this.courseRepository.findAllById(lecturerDto.getTeaches());
        lecturer.setFullName(lecturerDto.getFullname());
        lecturer.setEmail(lecturerDto.getEmail());
        lecturer.setDescription(lecturerDto.getDescription());
        lecturer.setTeaches(courses);

        return Optional.of(this.lecturerRepository.save(lecturer));
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

    @Override
    public List<Course> getTeaches(Long id) throws LecturerNotFound{
        Lecturer lecturer=lecturerRepository.findById(id).orElseThrow(() -> new LecturerNotFound(id));
        return lecturer.getTeaches();
    }

    @Override
    public Optional<Lecturer> addCourseToLecturer(Long id,Long courseId) throws LecturerNotFound, CourseNotFound{
        Lecturer lecturer = this.lecturerRepository.findById(id).orElseThrow(()-> new LecturerNotFound(id));
        Course course = this.courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFound(courseId));

        List<Course> courses = lecturer.getTeaches();
        courses.add(course);
        return Optional.of(this.lecturerRepository.save(lecturer));
    }
}