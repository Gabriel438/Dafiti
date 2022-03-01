package com.dafiti.challenge.model.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthForm {
    private String client;
    private String pass;
    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(client, pass);
    }
}
