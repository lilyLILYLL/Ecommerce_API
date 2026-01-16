package com.lilly.ecommerce_api.controllers;

import com.lilly.ecommerce_api.dtos.CartRecords;
import com.lilly.ecommerce_api.models.User;
import com.lilly.ecommerce_api.services.CartService;
import com.lilly.ecommerce_api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/items")
public class CartController {
     private final UserService userService;
     private final CartService cartService;

    public CartController(UserService userService, CartService cartService){
        this.userService = userService;
        this.cartService  =cartService;
    }
    @PostMapping
    public ResponseEntity<?> addItemToCart(@RequestBody CartRecords.AddItemToCartRequest request){
        User currentUser = userService.getAuthenticatedUser();
        cartService.addItemToCart(currentUser.getUserId(), request.productId(), request.quantity());
        return ResponseEntity.status(HttpStatus.OK).body("Added");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable String productId){
        User currentUser = userService.getAuthenticatedUser();
        cartService.removeItemFromCart(currentUser.getUserId(), productId);
        return ResponseEntity.ok("Removed");
    }

}
