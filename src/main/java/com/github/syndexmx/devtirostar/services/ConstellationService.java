package com.github.syndexmx.devtirostar.services;

import com.github.syndexmx.devtirostar.domain.Constellation;
import com.github.syndexmx.devtirostar.domain.Star;

import java.util.List;
import java.util.Optional;

public interface ConstellationService {

    boolean isConstellationExists(Constellation constellation);

    Constellation save(Constellation constellation);

    Optional<Constellation> findById(String designator);

    List<Constellation> listConstellations();

    void deleteConstellationById(String designator);
}
