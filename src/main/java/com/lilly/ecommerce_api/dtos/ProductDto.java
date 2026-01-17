package com.lilly.ecommerce_api.dtos;

public class ProductDto {
    public record ProductRecord( String productId,
                                 String name,
                                 double price,
                                 String description){}
}
