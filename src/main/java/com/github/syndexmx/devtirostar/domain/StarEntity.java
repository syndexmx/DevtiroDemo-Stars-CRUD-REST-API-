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
@Table(name = "stars")
public class StarEntity {

    @Id
    @Column(name = "star_id", length = 12)
    private String designator;

    @Column(name = "constellation_id", length = 12)
    private String inConstellation;

    @Column(name = "star_name", length = 20)
    private String name;

    @Column(name = "star_type", length = 8)
    private String type;


}
