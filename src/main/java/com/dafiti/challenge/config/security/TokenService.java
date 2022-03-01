package com.dafiti.challenge.config.security;

import java.util.Date;

import com.dafiti.challenge.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${dafiti.jwt.expiration}")
    private String expiration;
    
    @Value("${dafiti.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expirate = new Date(now.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
        .setIssuer("Dafiti API")
        .setSubject(user.getId().toString())
        .setIssuedAt(now)
        .setExpiration(expirate)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
    }

    public boolean isValidToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
    
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Long getIdToken(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
    
}
