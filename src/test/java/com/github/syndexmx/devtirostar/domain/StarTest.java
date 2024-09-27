package com.github.syndexmx.devtirostar.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StarTest {

    @Test
    public void testThatBuilderIsWorking() {
        Star star = Star.builder()
                .designator("aVir")
                .inConstellation("Virgo")
                .name("Spica")
                .type("B1AE4")
                .build();
        assertEquals(star.getDesignator(), "aVir");
        assertEquals(star.getInConstellation(), "Virgo");
        assertEquals(star.getName(), "Spica");
        assertEquals(star.getType(), "B1AE4");
    }
}
