package com.lilly.ecommerce_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carts")
@Data
public class Cart {
    @Id
    private String cartId;
    private String userId;
}
