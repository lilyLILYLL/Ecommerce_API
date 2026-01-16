package com.lilly.ecommerce_api.dtos;

import com.lilly.ecommerce_api.models.CartItem;

import java.util.List;

// CartRecords.java
public class CartRecords {
    public record AddItemToCartRequest(String productId, int quantity) {}
    public record RemoveItemFromCartRequest(String productId) {}
    public record GetCartResponse( double totalAmount,List<CartItem> items){}
}
