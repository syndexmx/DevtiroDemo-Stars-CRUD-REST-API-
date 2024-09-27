package com.github.syndexmx.devtirostar.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.devtirostar.domain.Constellation;
import com.github.syndexmx.devtirostar.services.ConstellationService;
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

import static com.github.syndexmx.devtirostar.TestData.testConstellation;
import static com.github.syndexmx.devtirostar.TestData.testStar;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConstellationControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConstellationService starService;

    @Test
    public void testThatConstellationIsCreated() throws Exception {
        final Constellation constellation = testConstellation();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String constellationJson = objectMapper.writeValueAsString(constellation);
        mockMvc.perform(MockMvcRequestBuilders.put("/constellations/"
                                + constellation.getDesignator())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(constellationJson))
                .andExpect(MockMvcResultMatchers.content().json(constellationJson));
    }

    @Test
    public void testThatConstellationIsUpdatedReturnsHttp200() throws Exception {
        final Constellation constellation = testConstellation();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String constellationJson = objectMapper.writeValueAsString(constellation);
        mockMvc.perform(MockMvcRequestBuilders.put("/constellations/"
                                + constellation.getDesignator())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(constellationJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.designator")
                        .value(constellation.getDesignator()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(constellation.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genitive")
                        .value(constellation.getGenitive()));
    }

    @Test
    public void testThatConstellationIsUpdatedReturnsHttp201() throws Exception {
        final Constellation constellation = testConstellation();
        starService.save(constellation);
        final String CORRECTED_GENITIVE = "Virginis";
        constellation.setGenitive(CORRECTED_GENITIVE);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String constellationJson = objectMapper.writeValueAsString(constellation);
        mockMvc.perform(MockMvcRequestBuilders.put("/constellations/" + constellation.getDesignator())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(constellationJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.designator")
                        .value(constellation.getDesignator()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(constellation.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genitive")
                        .value(CORRECTED_GENITIVE));
    }

    @Test
    public void testThatRetrieveConstellationReturns404WhenNotFound() throws Exception {
        final String nonExistentConstellationName = "Noconstellation";
        mockMvc.perform(MockMvcRequestBuilders.get("/constellations/" + nonExistentConstellationName))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveStarReturnsHttp200AndStarWhenExists() throws Exception {
        final Constellation constellation = testConstellation();
        starService.save(constellation);
        mockMvc.perform(MockMvcRequestBuilders.get("/constellations/" + constellation.getDesignator()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.designator")
                        .value(constellation.getDesignator()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(constellation.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genitive")
                        .value(constellation.getGenitive()));
    }

    @Test
    public void testThatListConstellationsReturnsHttp200EmptyListWhenNoConstellationsExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/constellations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatListConstellationsReturnsHttp200AndListWhenConstellationsExist() throws Exception {
        final Constellation constellation = testConstellation();
        starService.save(constellation);
        mockMvc.perform(MockMvcRequestBuilders.get("/constellations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].designator")
                        .value(constellation.getDesignator()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name")
                        .value(constellation.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].genitive")
                        .value(constellation.getGenitive()));
    }

    @Test
    public void testDeleteReturnsHttp204WhenConstellationDoesntExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/constellations/nonExistentConstellationDesignator"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDeleteReturnsHttp204WhenConstellationExists() throws Exception {
        final Constellation constellation = testConstellation();
        starService.save(constellation);
        mockMvc.perform(MockMvcRequestBuilders.delete("/constellations/" + constellation.getDesignator()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
