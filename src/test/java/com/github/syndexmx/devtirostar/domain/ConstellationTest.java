package com.github.syndexmx.devtirostar.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstellationTest {
    @Test
    public void testThatConstellationBuilderIsWorking() {
        Constellation constellation = Constellation.builder()
                .designator("Vir")
                .name("Virgo")
                .genitive("Virginis")
                .build();
        assertEquals(constellation.getDesignator(), "Vir");
        assertEquals(constellation.getName(), "Virgo");
        assertEquals(constellation.getGenitive(), "Virginis");
    }
}
