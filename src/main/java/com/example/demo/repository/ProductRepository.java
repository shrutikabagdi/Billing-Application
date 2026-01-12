package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Product product) {
        products.add(product);
    }

    public boolean deleteById(int id) {
        return products.removeIf(p -> p.getId() == id);
    }

    public boolean update(int id, Product newProduct) {
        Product existing = findById(id);
        if (existing != null) {
            existing.setPrice(newProduct.getPrice());
            existing.setStockQuantity(newProduct.getStockQuantity());
            existing.setName(newProduct.getName());
            existing.setGstPercentage(newProduct.getGstPercentage());
            return true;
        }
        return false;
    }
}
