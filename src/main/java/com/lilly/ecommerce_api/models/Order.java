package com.lilly.ecommerce_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("orders")
public class Order {
    @Id
    private String orderId;
    private String userId;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime orderedDate;
}
