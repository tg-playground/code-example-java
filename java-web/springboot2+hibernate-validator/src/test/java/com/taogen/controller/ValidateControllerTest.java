package com.taogen.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.entity.Input;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateControllerTest
{
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
        String invalidInput = "{numberBetweenOneAndTen: 1, ipAddress: 1.1.1}";
        mockMvc.perform(post("/validateRequestBody")
                .contentType("application/json")
                .content(invalidInput))
                .andExpect(status().isBadRequest());

        String validInput = "{\"numberBetweenOneAndTen\" : 1,  \"ipAddress\": \"1.1.1.1\"}";
        mockMvc.perform(post("/validateRequestBody")
                .contentType("application/json")
                .content(invalidInput))
                .andExpect(status().isOk());
    }

}
