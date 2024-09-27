package com.github.syndexmx.devtirostar.services.Impl;

import com.github.syndexmx.devtirostar.domain.Constellation;
import com.github.syndexmx.devtirostar.domain.ConstellationEntity;
import com.github.syndexmx.devtirostar.repositories.ConstellationRepository;
import com.github.syndexmx.devtirostar.repositories.StarRepository;
import com.github.syndexmx.devtirostar.services.ConstellationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ConstellationServiceImpl implements ConstellationService {

    private final ConstellationRepository constellationRepository;

    @Autowired
    public ConstellationServiceImpl(final ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
    }

    @Override
    public boolean isConstellationExists(Constellation constellation) {
        return constellationRepository.existsById(constellation.getDesignator());
    }

    @Override
    public Constellation save(final Constellation constellation) {
        final ConstellationEntity constellationEntity = constellationToConstellationEntity(constellation);
        final ConstellationEntity savedConstellationEntity = constellationRepository.save(constellationEntity);
        return constellationEntityToConstellation(savedConstellationEntity);
    }


    private ConstellationEntity constellationToConstellationEntity(Constellation constellation) {
        return ConstellationEntity.builder()
                .designator(constellation.getDesignator())
                .name(constellation.getName())
                .genitive(constellation.getGenitive())
                .build();
    }

    private Constellation constellationEntityToConstellation(ConstellationEntity constellationEntity) {
        return Constellation.builder()
                .designator(constellationEntity.getDesignator())
                .name(constellationEntity.getName())
                .genitive(constellationEntity.getGenitive())
                .build();
    }

    @Override
    public Optional<Constellation> findById(String designator) {
        final Optional<ConstellationEntity> foundConstellationEntity = constellationRepository.findById(designator);
        return foundConstellationEntity.map(constellationEntity -> constellationEntityToConstellation(constellationEntity));
    }

    @Override
    public List<Constellation> listConstellations() {
        final List<ConstellationEntity> foundStars = constellationRepository.findAll();
        return foundStars.stream().map(starEntity -> constellationEntityToConstellation(starEntity)).toList();
    }

    @Override
    public void deleteConstellationById(String designator) {
        try {
            constellationRepository.deleteById(designator);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("Attempted to delete non-existent constellation ", exception);
        };
    }
}
