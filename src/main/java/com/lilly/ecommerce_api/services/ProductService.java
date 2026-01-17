package com.lilly.ecommerce_api.services;

import com.lilly.ecommerce_api.dtos.ProductDto;
import com.lilly.ecommerce_api.models.Product;
import com.lilly.ecommerce_api.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    private ProductDto.ProductSummary mapToSummary(Product product){
        return new ProductDto.ProductSummary(product.getProductId(), product.getName(), product.getPrice(), product.getDescription());
    }

    public Page<ProductDto.ProductSummary> getAllAvailableProducts(Pageable pageable){
        // Find available products
        Page<Product> products = productRepository.findByStockQuantityGreaterThan(0, pageable);
       return  products.map(this::mapToSummary);

    }

    public Page<ProductDto.ProductSummary> searchByNameAndDescription(String keyword, Pageable pageable){
        // If the keyword is empty, return available products
        if(keyword ==null || keyword.isBlank()){
           return getAllAvailableProducts(pageable);
        }
        Page<Product> products= productRepository.searchByNameAndDescription(keyword, pageable);
        return products.map(this::mapToSummary);
    }


}
