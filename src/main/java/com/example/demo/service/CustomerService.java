package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    //Add Customer
    public void addCustomer(Customer customer) {
        try {
            repository.save(customer);
        }catch (Exception e) {
            throw new RuntimeException("Failed to add customer");
        }
    }

    //Get all customers
    public List<Customer> getAllCustomers() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch customer");
        }

    }

    // Get customer by ID
    public Customer getCustomerById(int id) {
        Customer customer = repository.findById(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with id " + id);
        }
        return customer;
    }

    public void updateCustomer(int id, Customer customer) {
        boolean updated = repository.update(id, customer);
        if (!updated) {
            throw new ResourceNotFoundException("Customer not found with id " + id);
        }
    }
}
