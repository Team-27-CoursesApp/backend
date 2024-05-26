package mk.ukim.finki.courses.web.rest;

import mk.ukim.finki.courses.model.Category;
import mk.ukim.finki.courses.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://courses-app-umkt.onrender.com")
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category create(@RequestBody Category category){
       return this.categoryService.create(category.getName(),category.getSlug());
    }

    @GetMapping
    public List<Category> findAll(){
        return this.categoryService.listCategories();
    }

    @GetMapping("/{slug}")
    public Category findBySlug(@PathVariable String slug){
        return this.categoryService.findBySlug(slug);
    }
}
