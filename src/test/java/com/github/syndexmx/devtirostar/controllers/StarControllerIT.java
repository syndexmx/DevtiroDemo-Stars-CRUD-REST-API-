package com.github.syndexmx.devtirostar.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.devtirostar.domain.Star;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.syndexmx.devtirostar.TestData.testStar;
import static com.github.syndexmx.devtirostar.TestData.testStarEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class StarControllerIT {

    private MockMvc mockMvc;

    @Test
    public void testThatStarIsCreated() throws Exception {
        final Star star = testStar();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String starJson = objectMapper.writeValueAsString(star);
        mockMvc.perform(MockMvcRequestBuilders.put("/stars/" + star.getDesignator())
                    .content("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(starJson));
    }
}
