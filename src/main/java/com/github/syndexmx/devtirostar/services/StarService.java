package com.github.syndexmx.devtirostar.services;

import com.github.syndexmx.devtirostar.domain.Star;

import java.util.List;
import java.util.Optional;

public interface StarService {

    boolean isStarExists(Star star);

    Star save(Star star);

    Optional<Star> findById(String designator);

    List<Star> listStars();

    void deleteStarById(String designator);

}
