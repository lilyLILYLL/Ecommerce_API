package com.lilly.ecommerce_api.controllers;

import com.lilly.ecommerce_api.dtos.AuthDto;
import com.lilly.ecommerce_api.models.User;
import com.lilly.ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController( UserService userService){
        this.userService = userService;
    }
    @PostMapping("/auth/register")
    public ResponseEntity<?> signup(@RequestBody AuthDto.SignUpRequest request){
        User createdUser = userService.registerUser(request.email(), request.password());
        return  ResponseEntity.ok("User registered successfully! ID: " + createdUser.getUserId());
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthDto.LogInResponse> login(@RequestBody AuthDto.LogInRequest request){
        String token = userService.logIn(request.email(), request.password());
        return  new ResponseEntity<>(new AuthDto.LogInResponse(token), HttpStatus.OK);
    }

}
