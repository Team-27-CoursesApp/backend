package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    public List<Course> listAllCoursesInShoppingCart(Long cartId);

    public Optional<ShoppingCart> addCourseInShoppingCart(String username, Long courseId);

    public Boolean deleteCourseInShoppingCart(String username, Long courseId);

    public Boolean deleteShoppingCart(String username);
}
