package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.DTO.GradeDto;
import mk.ukim.finki.courses.model.Grade;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.CourseUserNotFound;
import mk.ukim.finki.courses.repository.CommentRepository;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.repository.GradeRepository;
import mk.ukim.finki.courses.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final CourseRepository courseRepository;
    private final CourseUserRepository courseUserRepository;
    private final GradeRepository gradeRepository;

    public GradeServiceImpl(CourseRepository courseRepository, CourseUserRepository courseUserRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.courseUserRepository = courseUserRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public boolean saveGrade(GradeDto gradeDto) {
        Course course = this.courseRepository.findById(gradeDto.getCourseId()).orElseThrow(()->new CourseNotFound(gradeDto.getCourseId()));
        CourseUser user = this.courseUserRepository.findById(gradeDto.getUserId()).orElseThrow(()->new CourseUserNotFound(gradeDto.getUserId()));
        Grade grade = this.gradeRepository.findByUserAndCourse(user,course);
        if(grade != null){
            grade.setGrade(gradeDto.getGrade());
            gradeRepository.save(grade);
            updateCourseRating(course);
            return true;
        }
        gradeRepository.save(new Grade(course,user,gradeDto.getGrade()));
        updateCourseRating(course);
        return true;
    }

    @Override
    public int checkForGrade(Long courseId, Long userId ) {
        Course course = this.courseRepository.findById(courseId).orElseThrow(()->new CourseNotFound(courseId));
        CourseUser user = this.courseUserRepository.findById(userId).orElseThrow(()->new CourseUserNotFound(userId));
        int grade = 0;
        Grade g = gradeRepository.findByUserAndCourse(user,course);
        if(g != null){
            grade = g.getGrade();
        }
        return grade;
    }

    public void updateCourseRating(Course course){
        List<Grade>grades = this.gradeRepository.findByCourse(course);
        int gradesSum = grades.stream().mapToInt(Grade::getGrade).sum();
        course.setRating((float)gradesSum/grades.size());
        this.courseRepository.save(course);
    }

}
