package com.lilly.ecommerce_api.services;

import com.lilly.ecommerce_api.dtos.CartDto;
import com.lilly.ecommerce_api.models.Cart;
import com.lilly.ecommerce_api.models.CartItem;
import com.lilly.ecommerce_api.models.Product;
import com.lilly.ecommerce_api.repositories.CartRepository;
import com.lilly.ecommerce_api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {

    private  final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getOrCreateACart(String userId){
        // find cart by userId, If it doesn't exist, create a new one
        return cartRepository.findById(userId).orElseGet(()->{
            Cart cart  = new Cart(userId);
            return cartRepository.save(cart);
        });
    }

    public void addItemToCart(String userId, String productId, int quantity){
        // Find Product, check if it exists
        Product product = productRepository.findById(productId)
                                            .orElseThrow(() -> new NoSuchElementException("This product does not exist!"));

        // Get a Cart or create one if it doesn't exist
        Cart cart = getOrCreateACart(userId);

        // Check if item already exists in the cart
        Optional<CartItem> existingItemOpt = cart.getItems().stream().filter(item -> item.getProductId().equals(productId)).findFirst();

        if(existingItemOpt.isPresent()){
            // Check if there is enough stock
            CartItem existingItem = existingItemOpt.get();
            int totalQuantity = existingItem.getQuantity() + quantity;

            // Not enough stock
            if( totalQuantity > product.getStockQuantity()){
                throw new IllegalArgumentException("Not enough stock!");
            }

            existingItem.setQuantity(totalQuantity);
        }else{
            // create a new item
            CartItem newItem = new CartItem(productId, quantity, product.getPrice(), product.getName());
            cart.getItems().add(newItem); // add to cart

        }
        cartRepository.save(cart);
    }
    public void removeItemFromCart(String userId, String productId){
        // Get a Cart or create one if it doesn't exist
        Cart cart = getOrCreateACart(userId);

        // 2. Find and remove the item
        boolean removed = cart.getItems().removeIf(item -> item.getProductId().equals(productId));

        if(!removed){
            throw  new NoSuchElementException("Product not in the cart!");
        }

        // SAve the updated cart
        cartRepository.save(cart);

    }

    public CartDto.GetCartResponse getCart(String userId){
        // Get a Cart or create one if it doesn't exist
        Cart cart = getOrCreateACart(userId);

        return new CartDto.GetCartResponse(cart.getTotalAmount(),cart.getItems());

    }

    // Update Quantity Of An Item In Cart
    public void updateQuantityOfAnItemInCart(String userId, String productId, int newQuantity){
        // Get a Cart or create one if it doesn't exist
        Cart cart = getOrCreateACart(userId);

        // Check if the cartItem exists
       Optional<CartItem> existingItem = cart.getItems().stream().filter(item -> item.getProductId().equals(productId)).findFirst();
       if(existingItem.isEmpty()){
           throw new NoSuchElementException("Item not found!");
       }
       // Check if the product exits and if there is enough stock
       Product product = productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException("Product Not Found!"));
       if(product.getStockQuantity() < newQuantity){
           throw new IllegalArgumentException("Not enough stock!");
       }
        // Update with new quantity
       existingItem.get().setQuantity(newQuantity);

       // Save cart to the repository
        cartRepository.save(cart);
    }



}
