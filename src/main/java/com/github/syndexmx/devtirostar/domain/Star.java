package com.github.syndexmx.devtirostar.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Star {

    private String designator;

    private String inConstellation;

    private String name;

    private String type;

    private double distance;

    private double weight;

}
