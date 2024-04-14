package mk.ukim.finki.courses.model.exceptions;

public class LecturerNotFound extends RuntimeException {
    public LecturerNotFound(Long id) {
        super(String.format("Lecturer with id: %d was not found", id));
    }
}
