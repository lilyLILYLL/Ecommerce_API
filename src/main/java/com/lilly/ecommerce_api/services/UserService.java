package com.lilly.ecommerce_api.services;

import com.lilly.ecommerce_api.config.SecurityConfig;
import com.lilly.ecommerce_api.models.User;
import com.lilly.ecommerce_api.repositories.UserRepository;
import com.lilly.ecommerce_api.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil =  jwtUtil;
    }


    // Sign Up
    public User registerUser(String email, String password){
        // 1. Check if email already exists
        if(userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("Email is already in use!");
        }
        // 2. Hash password
        String hashedPassword = passwordEncoder.encode(password);// we have to hash it

        // 3. Save email, hashedPassword to the database
        User newUser = new User(email, hashedPassword);
        return userRepository.save(newUser);

    }

    // Login
    public String logIn(String email, String password){
        // Check if the user exist
        User user = userRepository.findByEmail(email).orElseThrow(() ->  new RuntimeException("Invalid credentials"));

        // Check if password is correct
        if(!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new RuntimeException("Password is incorrect");
        }
        //return JWT token
        return jwtUtil.generateToken(user.getEmail());
    }


}
