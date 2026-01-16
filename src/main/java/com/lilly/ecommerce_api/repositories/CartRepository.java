package com.lilly.ecommerce_api.repositories;

import com.lilly.ecommerce_api.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

}
