package com.dafiti.challenge.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void authBadRequestWithBodyNotValid() throws Exception{
        URI uri = new URI("/auth");
        String json = "{\"client\":\"\",\"pass\":\"\"}";
        mockMvc
        .perform(MockMvcRequestBuilders
            .post(uri)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void authBadRequestWithClientOrPassIsInvalid() throws Exception{
        URI uri = new URI("/auth");
        String json = "{\"client\":\"admin\",\"pass\":\"12222\"}";
        mockMvc
        .perform(MockMvcRequestBuilders
            .post(uri)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void authSuccessRequestWithBodyIsValid() throws Exception{
        URI uri = new URI("/auth");
        String json = "{\"client\":\"dafiti\",\"pass\":\"123\"}";
        mockMvc
        .perform(MockMvcRequestBuilders
            .post(uri)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


}
