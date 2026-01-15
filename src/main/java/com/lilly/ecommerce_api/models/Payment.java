package com.lilly.ecommerce_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "payments")
public class Payment {
    @Id
    private String paymentId;
    private String orderId;
    private double amount;
    private String provider;
    private PaymentStatus paymentStatus;

}
