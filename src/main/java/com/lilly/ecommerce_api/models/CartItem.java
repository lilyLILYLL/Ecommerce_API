package com.lilly.ecommerce_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cartItems")
public class CartItem {
    @Id
    private String cartItemId;
    private String cartId;
    private String productId;
    private int quantity;
}
