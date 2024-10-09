package com.demo.security.config;

import com.demo.security.entity.UserCredential;
import com.demo.security.repository.UserRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userCredential = userRepo.findByName(username);
        return userCredential.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("Invalid User found with username :"+username));

    }
}
