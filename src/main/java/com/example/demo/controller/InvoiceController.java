package com.example.demo.controller;

import com.example.demo.dto.InvoiceRequestDTO;
import com.example.demo.dto.InvoiceResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateInvoice(@RequestBody InvoiceRequestDTO request) {
        try {
            InvoiceResponseDTO response = service.generateInvoice(request);
            return ResponseEntity.ok(response);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getInvoiceById(id));
    }
}
