package mk.ukim.finki.courses.model.exceptions;

public class ShoppingCartNotFound extends RuntimeException{
    public ShoppingCartNotFound() { super("Shopping cart not found."); }
}
