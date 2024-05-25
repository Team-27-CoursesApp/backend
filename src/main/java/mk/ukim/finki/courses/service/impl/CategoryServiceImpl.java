package mk.ukim.finki.courses.service.impl;

import mk.ukim.finki.courses.model.Category;
import mk.ukim.finki.courses.repository.CategoryRepository;
import mk.ukim.finki.courses.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name, String slug) {
        if (name==null || name.isEmpty() || slug==null || slug.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,slug);
        return categoryRepository.save(c);
    }

    @Override
    public Category findBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAll();
    }
}
