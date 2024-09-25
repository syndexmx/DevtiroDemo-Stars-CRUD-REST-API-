package com.github.syndexmx.devtirostar.controllers;

import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.domain.StarEntity;
import com.github.syndexmx.devtirostar.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
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
        final ResponseEntity<Star> response =
                new ResponseEntity<>(star, HttpStatus.CREATED);
        return response;
    }
}
