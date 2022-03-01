package com.dafiti.challenge.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dafiti.challenge.model.User;
import com.dafiti.challenge.repository.UserRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilterToken extends OncePerRequestFilter{

    
    private TokenService tokenService;
    private UserRepository userRepository;

    public AuthenticationFilterToken(TokenService tokenService,UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
                String token = recoveryToken(request);

                boolean valid = tokenService.isValidToken(token);
                if(valid){
                    authClient(token);
                }
                filterChain.doFilter(request, response);
        
    }

    private void authClient(String token) {

        
        Long id = tokenService.getIdToken(token);

        User user = this.userRepository.findById(id).get();

        UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(loginData);
        
    }

    private String recoveryToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token == "" || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }else{
            return token.substring(7, token.length());
        }

    }
    
}
