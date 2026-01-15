package com.lilly.ecommerce_api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    private String userId;

    @Indexed(unique = true)
    private String email;
    private String name;
    private String passwordHash;
    private String address;

    public User(String email, String password_hash){
        this.email = email;
        this.passwordHash = password_hash;
    }

}
