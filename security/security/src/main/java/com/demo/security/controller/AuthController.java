package com.demo.security.controller;

import com.demo.security.dto.AuthReq;
import com.demo.security.entity.UserCredential;
import com.demo.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential userCredential){
        String res = authService.saveUser(userCredential);
        return res;
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthReq authReq){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getName(),authReq.getPassword()));
        if(auth.isAuthenticated()){
            return authService.generateToken(authReq.getName());
        }else{
            throw new RuntimeException("Invalid Access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam(name="token")String token){
        authService.validateToken(token);
        return "token Valid";
    }

}
