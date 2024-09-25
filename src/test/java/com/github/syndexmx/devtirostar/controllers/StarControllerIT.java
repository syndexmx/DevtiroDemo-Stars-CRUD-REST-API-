package com.github.syndexmx.devtirostar.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.devtirostar.domain.Star;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.syndexmx.devtirostar.TestData.testStar;
import static com.github.syndexmx.devtirostar.TestData.testStarEntity;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class StarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testThatStarIsCreated() throws Exception {
        final Star star = testStar();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String starJson = objectMapper.writeValueAsString(star);
        mockMvc.perform(MockMvcRequestBuilders.put("/stars/" + star.getDesignator())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(starJson))
                .andExpect(MockMvcResultMatchers.content().json(starJson));
    }

    @Test
    public void testThatStarIsCreatedJsonPathMatcher() throws Exception {
        final Star star = testStar();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String starJson = objectMapper.writeValueAsString(star);
        mockMvc.perform(MockMvcRequestBuilders.put("/stars/" + star.getDesignator())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(starJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.designator").value(star.getDesignator()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(star.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.constellation").value(star.getConstellation()));
    }
}
