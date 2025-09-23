package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.repository.CategoryRepository;
import java.util.List;
import com.example.demo.entity.Category;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Category getById(Long id) {
        return  repo.findById(id).orElse(null);
    }

    public Category create(Category category){
        return repo.save(category);
    }

    public Category update(Long id, Category categoryDetails) {
        Category category = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetails.getName());
        return repo.save(category);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
 }
