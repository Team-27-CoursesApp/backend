package mk.ukim.finki.courses.model.exceptions;

public class CategoryNotFound extends RuntimeException{
    public CategoryNotFound(Long id) {
        super(String.format("Course with id: %d was not found", id));
    }
}
