package com.dafiti.challenge.controller;

import javax.validation.Valid;

import com.dafiti.challenge.config.security.TokenService;
import com.dafiti.challenge.model.dto.AuthDTO;
import com.dafiti.challenge.model.form.AuthForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthDTO> auth(@RequestBody @Valid AuthForm form){
        UsernamePasswordAuthenticationToken loginData = form.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new AuthDTO(token,"Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
