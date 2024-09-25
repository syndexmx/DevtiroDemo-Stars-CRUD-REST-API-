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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StarServiceImplTest {

    @Mock
    private StarRepository starRepository;

    @InjectMocks
    private StarServiceImpl underTest;

    @Test
    public void testThatStarIsSaved() {
        final Star star = Star.builder()
                .designator("aAqi")
                .name("Altair")
                .constellation("Aquila")
                .build();

        final StarEntity starEntity = StarEntity.builder()
                .designator("aAqi")
                .name("Altair")
                .constellation("Aquila")
                .build();

        when(starRepository.save(eq(starEntity))).thenReturn(starEntity);
        final Star result = underTest.create(star);
        assertEquals(star, result);
    }

}
