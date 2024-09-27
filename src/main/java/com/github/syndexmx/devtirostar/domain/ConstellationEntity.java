package com.github.syndexmx.devtirostar.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "constellations")
public class ConstellationEntity {

    @Id
    @Column(name = "constellation_id", length = 6)
    private String designator;

    @Column(name = "constellation_name", length = 20)
    private String name;

    @Column(name = "constellation_gen", length = 20)
    private String genitive;

}
