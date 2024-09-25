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

}
