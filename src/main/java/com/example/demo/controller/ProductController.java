package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ProductService;
import com.example.demo.entity.Product;
import java.util.List;
import com.example.demo.dto.ProductDTO;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }


    @PostMapping
    public Product create(@RequestBody ProductDTO dto) {
        return productService.create(dto);
    }
 
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }


}
