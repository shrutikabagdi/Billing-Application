package com.example.demo.dto;

import java.util.List;

public class InvoiceRequestDTO {
    private int customerId;
    private double discount;
    private List<InvoiceItemRequestDTO> items;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<InvoiceItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemRequestDTO> items) {
        this.items = items;
    }
}
