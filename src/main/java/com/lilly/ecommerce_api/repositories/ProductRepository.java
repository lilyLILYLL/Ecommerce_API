package com.lilly.ecommerce_api.repositories;

import com.lilly.ecommerce_api.models.Product;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ '$or': [ { 'name': { '$regex': ?0, '$options': 'i' } }, { 'description': { '$regex': ?0, '$options': 'i' } } ] }")
    Page<Product> searchByNameAndDescription(String keyword, Pageable pageable);

    // Returns ONLY the 20 items you need, not all 10,000
    Page<Product> findByStockQuantityGreaterThan(int minStock, Pageable pageable);

}
