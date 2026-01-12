package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    public void save(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> findAll() {
        return customers;
    }

    public Customer findById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean update(int id, Customer newCustomer) {
        Customer existing = findById(id);
        if (existing != null) {
            existing.setName(newCustomer.getName());
            existing.setPhone(newCustomer.getPhone());
            existing.setEmail(newCustomer.getEmail());
            existing.setAddress(newCustomer.getAddress());
            return true;
        }
        return false;
    }
}
