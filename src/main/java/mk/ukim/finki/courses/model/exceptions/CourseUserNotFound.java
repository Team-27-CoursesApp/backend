package mk.ukim.finki.courses.model.exceptions;

public class CourseUserNotFound extends RuntimeException {
    public CourseUserNotFound(Long id) {
        super(String.format("Course user with id %d was not found", id));
    }
    public CourseUserNotFound() { super("Course user not found."); }
}
