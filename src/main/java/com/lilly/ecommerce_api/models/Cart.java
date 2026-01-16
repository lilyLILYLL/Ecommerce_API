package com.lilly.ecommerce_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
@Data
public class Cart {
    @Id
    private String cartId;
    private List<CartItem> items = new ArrayList<>(); // Embedded!

    public Cart(){}
    public Cart(String id){
        this.cartId = id;
    }

    public double getTotalAmount() {
        double total =0;
        for(CartItem item: items){
            total += item.getPrice()*item.getQuantity();
        }
        return total;
    }
}
