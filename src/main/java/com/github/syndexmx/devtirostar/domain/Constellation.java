package com.github.syndexmx.devtirostar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Constellation {

    private String designator;

    private String name;

    private String genitive;


}
