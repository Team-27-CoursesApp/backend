package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.ShoppingCart;
import mk.ukim.finki.courses.model.exceptions.CourseNotFound;
import mk.ukim.finki.courses.model.exceptions.ShoppingCartNotFound;
import mk.ukim.finki.courses.repository.CourseRepository;
import mk.ukim.finki.courses.repository.CourseUserRepository;
import mk.ukim.finki.courses.repository.ShoppingCartRepository;
import mk.ukim.finki.courses.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CourseUserRepository courseUserRepository;
    private final CourseRepository courseRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, CourseUserRepository courseUserRepository, CourseRepository courseRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.courseUserRepository = courseUserRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> listAllCoursesInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(cartId).orElseThrow(() -> new ShoppingCartNotFound(cartId));
        return shoppingCart.getCourses();
    }

    @Override
    public Optional<ShoppingCart> addCourseInShoppingCart(String username, Long courseId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserUsername(username).orElseThrow(ShoppingCartNotFound::new);
        List<Course> courses = shoppingCart.getCourses();
        Course newCourse = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));
        courses.add(newCourse);
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + newCourse.getPrice());

        return Optional.of(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public Boolean deleteCourseInShoppingCart(String username, Long courseId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserUsername(username).orElseThrow(ShoppingCartNotFound::new);
        Course courseToRemove = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));
        if(shoppingCart == null || courseToRemove == null) {
            return false;
        }
        List<Course> courses = shoppingCart.getCourses();
        courses.removeIf(course -> courseId.equals(course.getId()));
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - courseToRemove.getPrice());
        return true;
    }

    @Override
    public Boolean deleteShoppingCart(String username) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserUsername(username).orElseThrow(ShoppingCartNotFound::new);
        if(shoppingCart == null) {
            return false;
        }
        shoppingCartRepository.delete(shoppingCart);
        return true;
    }
}
