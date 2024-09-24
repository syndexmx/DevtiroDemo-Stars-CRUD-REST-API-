package com.github.syndexmx.devtirostar.services.Impl;

import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.domain.StarEntity;
import com.github.syndexmx.devtirostar.repositories.StarRepository;
import com.github.syndexmx.devtirostar.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;

    @Autowired
    public StarServiceImpl(final StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Override
    public Star create(final Star star) {
        final StarEntity starEntity = starToStarEntity(star);
        final StarEntity savedStarEntity = starRepository.save(starEntity);
        return starEntityToStar(savedStarEntity);
    }

    private StarEntity starToStarEntity(Star star) {
        return StarEntity.builder()
                .designator(star.getDesignator())
                .constellation(star.getConstellation())
                .name(star.getConstellation())
                .build();
    }

    private Star starEntityToStar(StarEntity starEntity) {
        return Star.builder()
                .designator(starEntity.getDesignator())
                .constellation(starEntity.getConstellation())
                .name(starEntity.getConstellation())
                .build();
    }
}
