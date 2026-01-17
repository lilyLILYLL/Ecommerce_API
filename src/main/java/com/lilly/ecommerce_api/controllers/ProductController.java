package com.lilly.ecommerce_api.controllers;

import com.lilly.ecommerce_api.dtos.ProductDto;
import com.lilly.ecommerce_api.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto.ProductRecord>> getAllProducts(){
        List<ProductDto.ProductRecord> availableProducts = productService.getAllAvailableProducts();
        return  ResponseEntity.status(HttpStatus.OK).body(availableProducts);
    }
}
