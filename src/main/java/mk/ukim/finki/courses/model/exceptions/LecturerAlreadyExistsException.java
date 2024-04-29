package mk.ukim.finki.courses.model.exceptions;

public class LecturerAlreadyExistsException extends RuntimeException{
    public LecturerAlreadyExistsException(){
        super("Lecturer already exists.");
    }
}
