package mk.ukim.finki.courses.web.rest;

import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.ShoppingCart;
import mk.ukim.finki.courses.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoppingCarts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<List<Course>> listAllCoursesInShoppingCart(@PathVariable Long shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.listAllCoursesInShoppingCart(shoppingCartId));
    }

    @PostMapping("/{username}/{courseId}")
    public ResponseEntity<ShoppingCart> addCourseInShoppingCart(@PathVariable String username,
                                                                @PathVariable Long courseId) {
        return ResponseEntity.ok(shoppingCartService.addCourseInShoppingCart(username, courseId).get());
    }

    @DeleteMapping("/{username}/{courseId}")
    public ResponseEntity<Boolean> deleteCourseInShoppingCart(@PathVariable String username,
                                                              @PathVariable Long courseId) {
        return ResponseEntity.ok(shoppingCartService.deleteCourseInShoppingCart(username, courseId));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Boolean> deleteShoppingCart(@PathVariable String username) {
        return ResponseEntity.ok(shoppingCartService.deleteShoppingCart(username));
    }
}
