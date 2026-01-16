package com.lilly.ecommerce_api.repositories;

import com.lilly.ecommerce_api.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
