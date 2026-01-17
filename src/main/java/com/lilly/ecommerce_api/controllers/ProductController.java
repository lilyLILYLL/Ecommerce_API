package com.lilly.ecommerce_api.controllers;

import com.lilly.ecommerce_api.dtos.ProductDto;
import com.lilly.ecommerce_api.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Page<ProductDto.ProductSummary>> getProducts(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10) Pageable pageable){
        if(search==null || search.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllAvailableProducts(pageable));
        }

        return ResponseEntity.status(HttpStatus.OK).body(productService.searchByNameAndDescription(search, pageable));


    }
}
