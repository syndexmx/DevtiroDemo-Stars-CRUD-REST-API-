package com.github.syndexmx.devtirostar.domain;

import org.junit.jupiter.api.Test;
import static com.github.syndexmx.devtirostar.TestData.testStar;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StarTest {

    @Test
    public void testThatBuilderIsWorking() {
        Star star = Star.builder()
                .designator("aVir")
                .constellation("Virgo")
                .name("Spica")
                .build();
        assertEquals(star.getDesignator(), "aVir");
        assertEquals(star.getConstellation(), "Virgo");
        assertEquals(star.getName(), "Spica");
    }
}
