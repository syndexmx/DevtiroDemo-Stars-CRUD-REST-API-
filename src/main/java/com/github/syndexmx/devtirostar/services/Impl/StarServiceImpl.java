package com.github.syndexmx.devtirostar.services.Impl;

import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.domain.StarEntity;
import com.github.syndexmx.devtirostar.repositories.StarRepository;
import com.github.syndexmx.devtirostar.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;

    @Autowired
    public StarServiceImpl(final StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Override
    public boolean isStarExists(Star star) {
        return starRepository.existsById(star.getDesignator());
    }

    @Override
    public Star save(final Star star) {
        final StarEntity starEntity = starToStarEntity(star);
        final StarEntity savedStarEntity = starRepository.save(starEntity);
        return starEntityToStar(savedStarEntity);
    }


    private StarEntity starToStarEntity(Star star) {
        return StarEntity.builder()
                .designator(star.getDesignator())
                .name(star.getName())
                .constellation(star.getConstellation())
                .build();
    }

    private Star starEntityToStar(StarEntity starEntity) {
        return Star.builder()
                .designator(starEntity.getDesignator())
                .name(starEntity.getName())
                .constellation(starEntity.getConstellation())
                .build();
    }

    @Override
    public Optional<Star> findById(String designator) {
        final Optional<StarEntity> foundStarEntity = starRepository.findById(designator);
        return foundStarEntity.map(starEntity -> starEntityToStar(starEntity));
    }

    @Override
    public List<Star> listStars() {
        final List<StarEntity> foundStars = starRepository.findAll();
        return foundStars.stream().map(starEntity -> starEntityToStar(starEntity)).toList();
    }
}
