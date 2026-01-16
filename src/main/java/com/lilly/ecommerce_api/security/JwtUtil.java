package com.lilly.ecommerce_api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // 1. SECRET KEY: In production, store this in application.properties!
    // Must be at least 256 bits (32 chars) long for HS256 security.
    @Value("${jwt.secret}")
    private String SECRET;

    // 2. Generate a Key object from the secret string
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // 3. Method to Generate Token
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email) // The "owner" of the token
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours valid
                .signWith(getSigningKey()) // Sign it with the key
                .compact();
    }

    // 4. Method to Validate Token (Basic check)
    // You can add more checks (like expiration) here
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // VERIFY
                .build() //Constructs the parser object
                .parseSignedClaims(token) //Decodes the token string back into its JSON structure
                .getPayload() // Extracts the "Claims" (the data inside the token, like email, expiration, etc.).
                .getSubject();// Return the "owner" of the token
    }
}
