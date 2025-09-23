package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;

@Service
public class ProductService {
    private final  ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public Product update(Long id, Product newP) {
        Product p = repo.findById(id).orElseThrow();
        p.setName(newP.getName());
        p.setPrice(newP.getPrice());
            return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
