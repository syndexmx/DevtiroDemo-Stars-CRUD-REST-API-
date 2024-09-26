package com.github.syndexmx.devtirostar.controllers;

import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.domain.StarEntity;
import com.github.syndexmx.devtirostar.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StarController {

    private final StarService starService;

    @Autowired
    public StarController(final StarService starService) {
        this.starService = starService;
    }

    @PutMapping(path = "/stars/{designator}")
    public ResponseEntity<Star> createStar(
            @PathVariable final String designator, @RequestBody final Star star) {
        star.setDesignator(designator);
        final Star savedStar = starService.create(star);
        final ResponseEntity<Star> response = new ResponseEntity<Star>(savedStar,
                HttpStatus.CREATED);
        return response;
    }

    @GetMapping(path = "/stars/{designator}")
    public ResponseEntity<Star> retrieveStar(@PathVariable final String designator) {
        final Optional<Star> foundStar = starService.findById(designator);
        return foundStar.map(star -> new ResponseEntity<Star>(star, HttpStatus.OK))
                .orElse(new ResponseEntity<Star>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/stars")
    public ResponseEntity<List<Star>> listStars() {
        return new ResponseEntity<List<Star>>(starService.listStars(), HttpStatus.OK);
    }
}
