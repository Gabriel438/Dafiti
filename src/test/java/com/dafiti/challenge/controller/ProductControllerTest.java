package com.dafiti.challenge.controller;

import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductControllerTest {
    
    @Autowired
    private MockMvc mockMvc;


    public String requestToken() throws Exception{
        String body = "{\"client\":\"dafiti\", \"pass\":\"123\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        return response.replace("\",\"type\":\"Bearer\"}", "");
    }

    @Test
    public void errorWhenGetAllProductsWithoutPageOrSize() throws Exception{
        URI uri = new URI("/product");
        mockMvc
        .perform(MockMvcRequestBuilders
            .get(uri)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void successWhenGetAllProducts() throws Exception{
        URI uri = new URI("/product");
        mockMvc
        .perform(MockMvcRequestBuilders
            .get(uri)
            .param("page", "0")
            .param("size", "10")  
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    public void createProductWithoutToken() throws Exception{
        URI uri = new URI("/product");
        mockMvc
        .perform(MockMvcRequestBuilders
            .post(uri)
        ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void createProductWithToken() throws Exception{
        String json = "{\"nome\":\"Test\",\"category\": {\"id\":1},\"price\": \"1.0\",\"size\": \"Size Test\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .header("Authorization", "Bearer " + requestToken()))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateProductWithToken() throws Exception{
        String json = "{\"nome\":\"Test Update\",\"category\": {\"id\":1},\"price\": \"1.0\",\"size\": \"Size Test\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/product/{id}",1)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .header("Authorization", "Bearer " + requestToken()))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
