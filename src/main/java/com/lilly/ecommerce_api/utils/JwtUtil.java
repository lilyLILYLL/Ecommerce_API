package com.lilly.ecommerce_api.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // 1. SECRET KEY: In production, store this in application.properties!
    // Must be at least 256 bits (32 chars) long for HS256 security.
    private static final String SECRET = "YourSuperSecretKeyThatMustBeVeryLongAndSecure123456";

    // 2. Generate a Key object from the secret string
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
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
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();// Return the "owner" of the token
    }
}
