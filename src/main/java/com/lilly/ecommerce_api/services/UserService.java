package com.lilly.ecommerce_api.services;

import com.lilly.ecommerce_api.models.User;
import com.lilly.ecommerce_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired UserRepository userRepository;

    // Sign Up
    public User registerUser(String email, String password){
        // 1. Check if email already exists
        if(userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("Email is already in use!");
        }
        // Hash password
        String hashedPassword = password;// we have to hash it

        // Save email, hashedPassword to the database
        User user = new User(email, hashedPassword);
        return userRepository.save(user);

    }


}
