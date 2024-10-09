package com.demo.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtService {

    public static final String SECRET = "5YSdMcIQf4oN4rvl/9lwYWsg72xLqI2PY2dPmbLbhFDYIPXUgJBMNqpeqQZfMgWs";

    public String generateToken(String name,String roles) {
        Map<String,Object> claims = new HashMap<>();
        List<String> role = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        claims.put("roles",role);
        return createToken(claims,name);
    }

    private String createToken(Map<String, Object> claims, String name) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }
}
