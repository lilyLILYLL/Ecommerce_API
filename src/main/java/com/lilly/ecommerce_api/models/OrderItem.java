package com.lilly.ecommerce_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orderItems")
public class OrderItem {
    @Id
    private String orderItemId;
    private String orderId;
    private String productId;
    private int quantity;
    private double unitPrice;
}
