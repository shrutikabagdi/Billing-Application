package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // POST /products
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        service.addProduct(product);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    // GET /products
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.getProductById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable int id,
            @RequestBody Product product) {
        try {
            service.updateProduct(id, product);
            return ResponseEntity.ok("Product updated successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            service.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
