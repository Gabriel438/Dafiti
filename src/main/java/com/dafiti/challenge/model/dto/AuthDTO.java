package com.dafiti.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {

    private String token;
    private String type;

    public AuthDTO(String token, String type) {
        this.token = token;
        this.type = type;
    }
    
}
