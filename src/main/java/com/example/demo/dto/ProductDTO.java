package com.example.demo.dto;
import java.util.Set;

public class ProductDTO {
    private String name;
    private Double price;
    private Set<Long> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Long> getCategoryIds() {
        return categories;
    }

    public void setCategoryIds(Set<Long> categories) {
        this.categories = categories;
    }
}
