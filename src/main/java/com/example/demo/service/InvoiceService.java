package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public InvoiceService(InvoiceRepository invoiceRepository,
                          CustomerRepository customerRepository,
                          ProductRepository productRepository) {

        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public InvoiceResponseDTO generateInvoice(InvoiceRequestDTO request) {

        Customer customer = customerRepository.findById(request.getCustomerId());
        if (customer == null)
            throw new ResourceNotFoundException("Customer not found");

        double subtotal = 0;
        double totalTax = 0;

        List<InvoiceItemResponseDTO> responseItems = new ArrayList<>();

        for (InvoiceItemRequestDTO itemReq : request.getItems()) {

            Product product = productRepository.findById(itemReq.getProductId());
            if (product == null)
                throw new ResourceNotFoundException("Product not found: " + itemReq.getProductId());

            int qty = itemReq.getQuantity();
            double price = product.getPrice() * qty;
            double tax = price * (product.getGstPercentage() / 100);
            double total = price + tax;

            subtotal += price;
            totalTax += tax;

            InvoiceItemResponseDTO itemRes = new InvoiceItemResponseDTO();
            itemRes.setProductId(product.getId());
            itemRes.setProductName(product.getName());
            itemRes.setQuantity(qty);
            itemRes.setPrice(product.getPrice());
            itemRes.setTaxAmount(tax);
            itemRes.setTotal(total);

            responseItems.add(itemRes);
        }

        InvoiceResponseDTO response = new InvoiceResponseDTO();
        response.setInvoiceId(invoiceRepository.findAll().size() + 1);
        response.setInvoiceDate(LocalDate.now());
        response.setCustomerId(request.getCustomerId());
        response.setItems(responseItems);
        response.setSubtotal(subtotal);
        response.setTotalTax(totalTax);
        response.setDiscount(request.getDiscount());
        response.setFinalAmount(subtotal + totalTax - request.getDiscount());

        invoiceRepository.save(response);

        return response;
    }

    public List<InvoiceResponseDTO> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public InvoiceResponseDTO getInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }
}
