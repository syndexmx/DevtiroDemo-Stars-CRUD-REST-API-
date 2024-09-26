package com.github.syndexmx.devtirostar.services.Impl;

import com.github.syndexmx.devtirostar.domain.Star;
import com.github.syndexmx.devtirostar.domain.StarEntity;
import com.github.syndexmx.devtirostar.repositories.StarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static com.github.syndexmx.devtirostar.TestData.testStar;
import static com.github.syndexmx.devtirostar.TestData.testStarEntity;

@ExtendWith(MockitoExtension.class)
public class StarServiceImplTest {

    @Mock
    private StarRepository starRepository;

    @InjectMocks
    private StarServiceImpl underTest;

    @Test
    public void testThatStarIsSaved() {
        final Star star = testStar();

        final StarEntity starEntity = testStarEntity();

        when(starRepository.save(eq(starEntity))).thenReturn(starEntity);
        final Star result = underTest.create(star);
        assertEquals(star, result);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoStar() {
        final String nonExistantStarName = "Nostar";
        when(starRepository.findById(eq(nonExistantStarName))).thenReturn(Optional.empty());
        final Optional<Star> result = underTest.findById(nonExistantStarName);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnsStarWhenExists() {
        final Star star = testStar();
        final StarEntity starEntity = testStarEntity();
        when(starRepository.findById(eq(star.getDesignator()))).thenReturn(Optional.of(starEntity));
        final Optional<Star> result = underTest.findById(star.getDesignator());
        assertEquals(Optional.of(star), result);
    }

    @Test
    public void testThatListStarsReturnsEmptyListWhenNoStarsExists() {
        when(starRepository.findAll()).thenReturn(new ArrayList<StarEntity>());
        final List<Star> result = underTest.listStars();
        assertEquals(0, result.size());
    }

    @Test
    public void testThatListStarsReturnsListWhenStarsExist() {
        final StarEntity starEntity = testStarEntity();
        when(starRepository.findAll()).thenReturn(List.of(starEntity));
        final List<Star> result = underTest.listStars();
        assertEquals(1, result.size());
    }
}
