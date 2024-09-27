package com.github.syndexmx.devtirostar.controllers;

import com.github.syndexmx.devtirostar.domain.Constellation;
import com.github.syndexmx.devtirostar.services.ConstellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConstellationController {
    private final ConstellationService constellationService;

    @Autowired
    public ConstellationController(final ConstellationService constellationService) {
        this.constellationService = constellationService;
    }

    @PutMapping(path = "/constellations/{designator}")
    public ResponseEntity<Constellation> createUpdateStar(
            @PathVariable final String designator, @RequestBody final Constellation constellation) {
        constellation.setDesignator(designator);
        final boolean isConstellationExists = constellationService.isConstellationExists(constellation);
        final Constellation savedConstellation = constellationService.save(constellation);
        if (isConstellationExists) {
            return new ResponseEntity<Constellation>(savedConstellation, HttpStatus.OK);
        } else {
            return new ResponseEntity<Constellation>(savedConstellation, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/constellations/{designator}")
    public ResponseEntity<Constellation> retrieveConstellation(@PathVariable final String designator) {
        final Optional<Constellation> foundConstellation = constellationService.findById(designator);
        return foundConstellation.map(constellation ->
                        new ResponseEntity<Constellation>(constellation, HttpStatus.OK))
                .orElse(new ResponseEntity<Constellation>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/constellations")
    public ResponseEntity<List<Constellation>> listConstellation() {
        return new ResponseEntity<List<Constellation>>(constellationService
                .listConstellations(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/constellations/{designator}")
    public ResponseEntity deleteConstellation(@PathVariable final String designator) {
        constellationService.deleteConstellationById(designator);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
