package com.demo.security.service;

import com.demo.security.config.SecurityConfig;
import com.demo.security.entity.UserCredential;
import com.demo.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SecurityConfig config;
    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential userCredential) {
        userCredential.setPassword(config.passwordEncoder().encode(userCredential.getPassword()));
        userRepo.save(userCredential);
        return "user added successfully";
    }

    public String generateToken(String name) {
        Optional<UserCredential> user = userRepo.findByName(name);
        return jwtService.generateToken(name,user.get().getRoles());
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
