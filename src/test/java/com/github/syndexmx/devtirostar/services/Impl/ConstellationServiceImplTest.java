package com.github.syndexmx.devtirostar.services.Impl;

import com.github.syndexmx.devtirostar.domain.Constellation;
import com.github.syndexmx.devtirostar.domain.ConstellationEntity;
import com.github.syndexmx.devtirostar.repositories.ConstellationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.devtirostar.TestData.testConstellationEntity;
import static com.github.syndexmx.devtirostar.TestData.testConstellation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConstellationServiceImplTest {
    @Mock
    private ConstellationRepository constellationRepository;

    @InjectMocks
    private ConstellationServiceImpl underTest;

    @Test
    public void testConstellationIsSaved() {
        final Constellation constellation = testConstellation();

        final ConstellationEntity constellationEntity = testConstellationEntity();

        when(constellationRepository.save(eq(constellationEntity))).thenReturn(constellationEntity);
        final Constellation result = underTest.save(constellation);
        assertEquals(constellation, result);
    }

    @Test
    public void testFindByIdReturnsEmptyWhenNoConstellation() {
        final String nonExistentConstellationName = "Noconstellation";
        when(constellationRepository.findById(eq(nonExistentConstellationName)))
                .thenReturn(Optional.empty());
        final Optional<Constellation> result = underTest.findById(nonExistentConstellationName);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testFindByIdReturnsConstellationWhenExists() {
        final Constellation constellation = testConstellation();
        final ConstellationEntity constellationEntity = testConstellationEntity();
        when(constellationRepository.findById(eq(constellation.getDesignator())))
                .thenReturn(Optional.of(constellationEntity));
        final Optional<Constellation> result = underTest.findById(constellation.getDesignator());
        assertEquals(Optional.of(constellation), result);
    }

    @Test
    public void testListConstellationsReturnsEmptyListWhenNoConstellationsExists() {
        when(constellationRepository.findAll()).thenReturn(new ArrayList<ConstellationEntity>());
        final List<Constellation> result = underTest.listConstellations();
        assertEquals(0, result.size());
    }

    @Test
    public void testListStarsReturnsListWhenStarsExist() {
        final ConstellationEntity constellationEntity = testConstellationEntity();
        when(constellationRepository.findAll()).thenReturn(List.of(constellationEntity));
        final List<Constellation> result = underTest.listConstellations();
        assertEquals(1, result.size());
    }

    @Test
    public void testIsConstellationExistsReturnsFalseWhenConstellationDoesntExist() {
        when(constellationRepository.existsById(any())).thenReturn(false);
        final boolean result = underTest.isConstellationExists(testConstellation());
        assertEquals(false, result);
    }

    @Test
    public void testIsConstellationExistsReturnsTrueWhenConstellationDoesExist() {
        when(constellationRepository.existsById(testConstellation().getDesignator())).thenReturn(true);
        final boolean result = underTest.isConstellationExists(testConstellation());
        assertEquals(true, result);
    }

    @Test
    public void testDeleteConstellationDeletes () {
        final String designator = "NonExistentDesignator";
        underTest.deleteConstellationById(designator);
        verify(constellationRepository, times(1)).deleteById(eq(designator));
    }
}
