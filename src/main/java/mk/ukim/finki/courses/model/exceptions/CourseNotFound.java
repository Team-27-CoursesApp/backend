package mk.ukim.finki.courses.model.exceptions;

public class CourseNotFound extends RuntimeException{

    public CourseNotFound(Long id) {
        super(String.format("Course with id: %d was not found", id));
    }
}
