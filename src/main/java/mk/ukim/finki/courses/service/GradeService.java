package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.DTO.GradeDto;
import mk.ukim.finki.courses.model.Grade;

public interface GradeService {
    boolean saveGrade(GradeDto gradeDto);
    int checkForGrade(Long courseId, Long userId);
}
