package com.github.syndexmx.devtirostar.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StarTest {

    @Test
    public void testThatBuilderIsWorking() {
        Star star = Star.builder()
                .designator("aVir")
                .inConstellation("Virgo")
                .name("Spica")
                .type("B1AE4")
                .distance(303.0f)
                .weight(11.43f)
                .build();
        assertEquals(star.getDesignator(), "aVir");
        assertEquals(star.getInConstellation(), "Virgo");
        assertEquals(star.getName(), "Spica");
        assertEquals(star.getType(), "B1AE4");
        double EPSILON_MAX_ALLOWED_DIVERGENCE = 0.1f;
        assertTrue(Math.abs(star.getDistance() - 303.43) < EPSILON_MAX_ALLOWED_DIVERGENCE);
        assertTrue(Math.abs(star.getWeight() - 11.43f) < EPSILON_MAX_ALLOWED_DIVERGENCE);
    }
}
