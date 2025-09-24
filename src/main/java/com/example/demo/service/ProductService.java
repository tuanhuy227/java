package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.dto.ProductDTO;
import java.util.Set;
import com.example.demo.entity.Category;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.util.Optional;
import java.util.HashSet;

@Service
public class ProductService {
    private final  ProductRepository repo;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repo, CategoryRepository categoryRepository) {
        this.repo = repo;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product create(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        Set<Category> categories = Optional.ofNullable(dto.getCategoryIds())
         .map(ids -> categoryRepository.findAllById(ids)
         .stream()
         .collect(Collectors.toSet()))
         .orElse(new HashSet<>());
        product.setCategories(categories);

        return repo.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product update(Long id, ProductDTO dto) {
        Product p = repo.findById(id).orElseThrow();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());

        Set<Category> categories = Optional.ofNullable(dto.getCategoryIds())
         .map(ids -> categoryRepository.findAllById(ids)
         .stream()
         .collect(Collectors.toSet()))
         .orElse(new HashSet<>());
        p.setCategories(categories);

        return repo.save(p);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
