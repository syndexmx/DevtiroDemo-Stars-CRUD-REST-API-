package com.github.syndexmx.devtirostar.services;

import com.github.syndexmx.devtirostar.domain.Star;

import java.util.List;
import java.util.Optional;

public interface StarService {

    Star create(Star star);

    Optional<Star> findById(String designator);

    List<Star> listStars();

}
