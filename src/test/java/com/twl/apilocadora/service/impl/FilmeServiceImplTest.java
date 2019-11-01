package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.model.Filme;
import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.repository.FilmeRepository;
import com.twl.apilocadora.repository.InventarioFilmeRepository;
import com.twl.apilocadora.util.MatcherUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
class FilmeServiceImplTest {

    @Mock
    private FilmeRepository filmeRepository;

    @Mock
    private InventarioFilmeRepository inventarioFilmeRepository;

    @InjectMocks
    private FilmeServiceImpl service;

    @BeforeEach
    void setUp() {
        initMocks(this);
        service = new FilmeServiceImpl(filmeRepository, inventarioFilmeRepository);
    }

    @Test
    public void shouldSuccessFindBy() {

        Filme filmeExample = new Filme();
        filmeExample.setIdFilme(1L);

        when(filmeRepository.findAll(Example.of(filmeExample, MatcherUtils.matchAnyContainingIgnoreCase())))
                .thenReturn(Collections.singletonList(getMockMovie()));

        assertFalse(service.findBy(1L, null, null).isEmpty());
    }

    @Test
    public void shouldFailFindBy() {

        Filme filmeExample = new Filme();
        filmeExample.setIdFilme(1L);

        when(filmeRepository.findAll(Example.of(filmeExample, MatcherUtils.matchAnyContainingIgnoreCase())))
                .thenReturn(Collections.emptyList());

        assertTrue(service.findBy(1L, null, null).isEmpty());
    }

    @Test
    public void shouldSuccessDeleteById() {
        when(inventarioFilmeRepository.findAllByIdFilme(1L)).thenReturn(Collections.emptyList());

        service.deleteById(1L);

        verify(filmeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldFailDeleteById() {
        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdUsuario(1L);

        when(inventarioFilmeRepository.findAllByIdFilme(1L)).thenReturn(Collections.singletonList(inventarioFilme));

        assertThrows(RuntimeException.class, () -> service.deleteById(1L));
    }

    private Filme getMockMovie() {
        return new Filme(1L, "filme teste", "diretor teste");
    }
}