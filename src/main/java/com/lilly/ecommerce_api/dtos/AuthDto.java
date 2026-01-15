package com.lilly.ecommerce_api.dtos;

public class  AuthDto{
    public record SignUpRequest(String email, String password){}
    public record LogInRequest(String email, String password){}
    public record LogInResponse(String token){}

}
