package com.github.syndexmx.devtirostar;

import com.github.syndexmx.devtirostar.domain.Constellation;
import com.github.syndexmx.devtirostar.domain.ConstellationEntity;
import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.domain.StarEntity;

public final class TestData {
    private TestData(){
    }

    public static Star testStar() {
        return Star.builder()
                .designator("aAqi")
                .name("Altair")
                .inConstellation("Aqu")
                .type("A7V")
                .distance(17.0f)
                .weight(18.0f)
                .build();
    }

    public static StarEntity testStarEntity() {
        return StarEntity.builder()
                .designator("aAqi")
                .name("Altair")
                .inConstellation(testConstellationEntity())
                .type("A7V")
                .distance(17.0f)
                .weight(18.0f)
                .build();
    }

    public static Constellation testConstellation() {
        return Constellation.builder()
                .designator("Aqu")
                .name("Aquila")
                .genitive("Aquilae")
                .build();
    }

    public static ConstellationEntity testConstellationEntity() {
        return ConstellationEntity.builder()
                .designator("Aqu")
                .name("Aquila")
                .genitive("Aquilae")
                .build();
    }


}