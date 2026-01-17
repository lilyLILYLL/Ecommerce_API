package com.lilly.ecommerce_api.services;

import com.lilly.ecommerce_api.dtos.ProductDto;
import com.lilly.ecommerce_api.models.Product;
import com.lilly.ecommerce_api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductDto.ProductRecord> getAllAvailableProducts(){
        List<Product> products = productRepository.findAll();
       return  products.stream().filter(product -> product.getStockQuantity() >0 )
               .map(product -> new ProductDto.ProductRecord(product.getProductId(), product.getName(), product.getPrice(), product.getDescription())).toList();

    }

}
