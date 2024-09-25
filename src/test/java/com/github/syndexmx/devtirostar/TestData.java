package com.github.syndexmx.devtirostar;

import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.domain.StarEntity;

public final class TestData {
    private TestData(){
    }

    public static Star testStar() {
        return Star.builder()
                .designator("aAqi")
                .name("Altair")
                .constellation("Aquila")
                .build();
    }

    public static StarEntity testStarEntity() {
        return StarEntity.builder()
                .designator("aAqi")
                .name("Altair")
                .constellation("Aquila")
                .build();
    }
}