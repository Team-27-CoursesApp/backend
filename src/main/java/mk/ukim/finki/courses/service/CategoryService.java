package mk.ukim.finki.courses.service;

import mk.ukim.finki.courses.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(String name, String slug);
    Category findBySlug(String slug);
    List<Category>listCategories();
}
