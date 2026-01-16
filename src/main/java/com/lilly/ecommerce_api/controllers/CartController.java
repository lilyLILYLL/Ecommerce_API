package com.lilly.ecommerce_api.controllers;

import com.lilly.ecommerce_api.dtos.AddItemToCartRequest;
import com.lilly.ecommerce_api.models.User;
import com.lilly.ecommerce_api.services.CartService;
import com.lilly.ecommerce_api.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
     private final UserService userService;
     private final CartService cartService;

    public CartController(UserService userService, CartService cartService){
        this.userService = userService;
        this.cartService  =cartService;
    }
    @PostMapping
    public ResponseEntity<?> addItemToCart(@RequestBody AddItemToCartRequest request){
        User currentUser = userService.getAuthenticatedUser();
        cartService.addItemToCart(currentUser.getUserId(), request.productId(), request.quantity());
        return ResponseEntity.ok("Added");
    }

}
