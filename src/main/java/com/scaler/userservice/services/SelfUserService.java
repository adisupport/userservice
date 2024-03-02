package com.scaler.userservice.services;

import com.scaler.userservice.execptions.NoUserFoundException;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repository.TokenRepo;
import com.scaler.userservice.repository.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;


@Service("SelfUserService")
public class SelfUserService implements UserService{
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    TokenRepo tokenRepo;
    SecretKey key;
    @Autowired
    public SelfUserService(UserRepo userRepo,
                           PasswordEncoder passwordEncoder, TokenRepo tokenRepo){
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.key = Jwts.SIG.HS256.key().build();
    }
    @Override
    public User addUser(String name, String email, String raw_password){
        User user = new User();
        String password = passwordEncoder.encode(raw_password);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public String login(String email,String password) throws NoUserFoundException{
        List<User> userList = userRepo.findByEmail(email);
        for(User user:userList){
            if(passwordEncoder.matches(password,user.getPassword())){
                // Generate Token
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", email); // Add your claims
                claims.put("userID",userRepo.findByEmail(email).get(0).getId());
                claims.put("role", "admin");

                return  Jwts.builder()
                        .subject("login") // Set subject (optional)
                        .issuedAt(new Date()) // Set issued at time (optional)
                        .expiration(new Date(System.currentTimeMillis() + 3600000)) // Set expiration (optional)
                        .claims(claims) // Set claims
                        .signWith(this.key) // Sign with secret key
                        .compact();
            }
        }
        System.out.println(
                email + " " + password
        );
        throw new NoUserFoundException(email,password);
    }


    @Override
    public User ValidateToken(String token) {
        Jws<Claims> claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
        Long userid = claims.getPayload().get("userID",Long.class);
        Optional<User> user = userRepo.findById(userid);
        return user.orElse(null);
    }
}
