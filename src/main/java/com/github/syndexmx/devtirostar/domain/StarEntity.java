package com.github.syndexmx.devtirostar.domain;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "constellation_id")
    private ConstellationEntity inConstellation;

    @Column(name = "star_name", length = 20)
    private String name;

    @Column(name = "star_type", length = 8)
    private String type;

    @Column(name = "distance_ly")
    private double distance;

    @Column(name = "weight_sw")
    private double weight;

    public String getInConstellationId() {
        return inConstellation.getName();
    }


}
