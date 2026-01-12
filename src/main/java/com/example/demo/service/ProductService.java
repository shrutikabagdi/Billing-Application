package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        Product product = repository.findById(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        return product;
    }

    public void updateProduct(int id, Product product) {
        boolean updated = repository.update(id, product);
        if (!updated) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
    }

    public void deleteProduct(int id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
    }
}
