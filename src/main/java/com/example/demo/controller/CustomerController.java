package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // POST /customers
    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        service.addCustomer(customer);
        return new ResponseEntity<>("Customer added successfully", HttpStatus.CREATED);
    }

    // GET /customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    // GET /customers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.getCustomerById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /customers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable int id,
            @RequestBody Customer customer) {
        try {
            service.updateCustomer(id, customer);
            return ResponseEntity.ok("Customer updated successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
