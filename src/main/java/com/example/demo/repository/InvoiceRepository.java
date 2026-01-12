package com.example.demo.repository;

import com.example.demo.dto.InvoiceResponseDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepository {

    private final List<InvoiceResponseDTO> invoices = new ArrayList<>();

    public void save(InvoiceResponseDTO invoice) {
        invoices.add(invoice);
    }

    public List<InvoiceResponseDTO> findAll() {
        return invoices;
    }

    public InvoiceResponseDTO findById(int id) {
        return invoices.stream()
                .filter(i -> i.getInvoiceId() == id)
                .findFirst()
                .orElse(null);
    }
}
