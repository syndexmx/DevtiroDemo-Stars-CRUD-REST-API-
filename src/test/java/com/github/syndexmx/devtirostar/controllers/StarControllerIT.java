package com.github.syndexmx.devtirostar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.services.StarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.syndexmx.devtirostar.TestData.testStar;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StarService starService;

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

    @Test
    public void testThatRetrieveStarReturns404WhenNotFound() throws Exception {
        final String nonExistantStarName = "Nostar";
        mockMvc.perform(MockMvcRequestBuilders.get("/stars/" + nonExistantStarName))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveStarReturnsHttp200AndStarWhenExists() throws Exception {
        final Star star = testStar();
        starService.save(star);
        mockMvc.perform(MockMvcRequestBuilders.get("/stars/" + star.getDesignator()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.designator").value(star.getDesignator()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(star.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.constellation").value(star.getConstellation()));
    }

    @Test
    public void testThatListStarsReturnsHttp200EmptyListWhenNoStarsExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatListStarsReturnsHttp200AndListWhenStarsExist() throws Exception {
        final Star star = testStar();
        starService.save(star);
        mockMvc.perform(MockMvcRequestBuilders.get("/stars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].designator").value(star.getDesignator()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(star.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].constellation").value(star.getConstellation()));

    }
}
