package com.lilly.ecommerce_api.models;

import lombok.Data;

@Data
public class CartItem {
    private String productId;
    private int quantity;
    private double price;
    private String name; // Snapshots name to reduce lookups

    public CartItem(String productId, int quantity, double price, String name){
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }
}
