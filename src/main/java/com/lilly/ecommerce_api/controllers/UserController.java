package com.lilly.ecommerce_api.controllers;

import com.lilly.ecommerce_api.dtos.SignUpRequest;
import com.lilly.ecommerce_api.models.User;
import com.lilly.ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired  UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request){
        User createdUser = userService.registerUser(request.email(), request.password());
        return  ResponseEntity.ok("User registered successfully! ID: " + createdUser.getUserId());
    }

}
