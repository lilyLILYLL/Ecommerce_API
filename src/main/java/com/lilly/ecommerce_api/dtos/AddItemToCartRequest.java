package com.lilly.ecommerce_api.dtos;

public record AddItemToCartRequest(String productId, int quantity) {
}
