package com.github.syndexmx.devtirostar.domain;

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

    private String constellation;

    private String name;

}
