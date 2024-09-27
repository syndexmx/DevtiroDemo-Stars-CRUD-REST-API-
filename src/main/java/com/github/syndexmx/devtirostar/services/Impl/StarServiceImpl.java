package com.github.syndexmx.devtirostar.services.Impl;

import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.domain.StarEntity;
import com.github.syndexmx.devtirostar.repositories.StarRepository;
import com.github.syndexmx.devtirostar.services.StarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
                .inConstellation(star.getInConstellation())
                .type(star.getType())
                .weight(star.getWeight())
                .distance(star.getDistance())
                .build();
    }

    private Star starEntityToStar(StarEntity starEntity) {
        return Star.builder()
                .designator(starEntity.getDesignator())
                .name(starEntity.getName())
                .inConstellation(starEntity.getInConstellation())
                .type(starEntity.getType())
                .weight(starEntity.getWeight())
                .distance(starEntity.getDistance())
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

    @Override
    public void deleteStarById(String designator) {
        try {
            starRepository.deleteById(designator);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("Attempted to delete non-existent star ", exception);
        };
    }
}
