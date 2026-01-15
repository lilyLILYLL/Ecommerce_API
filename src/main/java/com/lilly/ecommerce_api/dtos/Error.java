package com.lilly.ecommerce_api.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record Error(int status, String errorMessage, LocalDateTime timestamp) {
    public Error(int status, String errorMessage){
        this(status, errorMessage, LocalDateTime.now());
    }
}
