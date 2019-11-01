package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.dto.LocacaoDto;
import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.repository.InventarioFilmeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
class InventarioFilmeServiceImplTest {

    @Mock
    private InventarioFilmeRepository inventarioFilmeRepository;

    @InjectMocks
    private InventarioFilmeServiceImpl service;

    @BeforeEach
    void setUp() {
        initMocks(this);
        service = new InventarioFilmeServiceImpl(inventarioFilmeRepository);
    }

    @Test
    public void shouldSuccessCountByIdFilme() {
        when(inventarioFilmeRepository.countByIdFilme(1L)).thenReturn(1);

        Integer count = service.countByIdFilme(1L);

        assertEquals(count, 1);
    }

    @Test
    public void shouldSuccessCountAvailableByIdFilme() {
        when(inventarioFilmeRepository.countByIdFilmeAndIdUsuarioIsNull(1L)).thenReturn(1);

        Integer count = service.countAvailableByIdFilme(1L);

        assertEquals(count, 1);
    }

    @Test
    public void shouldSuccessFindAllByIdFilme() {

        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdFilme(1L);

        when(inventarioFilmeRepository.findAllByIdFilme(1L)).thenReturn(Collections.singletonList(inventarioFilme));

        assertFalse(service.findAllByIdFilme(1L).isEmpty());
    }

    @Test
    public void shouldFailFindAllByIdFilme() {

        when(inventarioFilmeRepository.findAllByIdFilme(1L)).thenReturn(Collections.emptyList());

        assertTrue(service.findAllByIdFilme(1L).isEmpty());
    }

    @Test
    public void shouldSuccessRent() {

        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdInventarioFilme(1L);
        inventarioFilme.setIdFilme(1L);

        InventarioFilme inventarioFilmeExpected = new InventarioFilme();
        inventarioFilmeExpected.setIdInventarioFilme(1L);
        inventarioFilmeExpected.setIdFilme(1L);
        inventarioFilmeExpected.setIdUsuario(1L);

        LocacaoDto locacaoDto = new LocacaoDto();
        locacaoDto.setIdsFilme(Collections.singletonList(1L));
        locacaoDto.setIdUsuario(1L);

        when(inventarioFilmeRepository.findFirstByIdFilmeAndIdUsuarioIsNull(1L)).thenReturn(inventarioFilme);
        inventarioFilme.setIdUsuario(1L);
        when(inventarioFilmeRepository.saveAll(Collections.singletonList(inventarioFilme)))
                .thenReturn(Collections.singletonList(inventarioFilmeExpected));

        List<InventarioFilme> inventarioFilmeList = service.rent(locacaoDto);

        assertEquals(inventarioFilmeExpected, inventarioFilmeList.stream().findFirst().orElse(null));
    }

    @Test
    public void shouldFailRent() {

        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdInventarioFilme(1L);
        inventarioFilme.setIdFilme(1L);

        LocacaoDto locacaoDto = new LocacaoDto();
        locacaoDto.setIdsFilme(Collections.singletonList(1L));
        locacaoDto.setIdUsuario(1L);

        when(inventarioFilmeRepository.findFirstByIdFilmeAndIdUsuarioIsNull(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> service.rent(locacaoDto));
    }

    @Test
    public void shouldSuccessReceiveAll() {

        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdInventarioFilme(1L);
        inventarioFilme.setIdUsuario(1L);
        inventarioFilme.setIdFilme(1L);

        List<InventarioFilme> inventarioFilmeList = Collections.singletonList(inventarioFilme);

        when(inventarioFilmeRepository.findAllByIdUsuario(1L)).thenReturn(inventarioFilmeList);

        service.receiveAll(1L);

        verify(inventarioFilmeRepository, times(1)).saveAll(inventarioFilmeList);
    }

    @Test
    public void shouldFailReceiveAll() {

        when(inventarioFilmeRepository.findAllByIdUsuario(1L)).thenReturn(Collections.emptyList());

        assertThrows(RuntimeException.class, () -> service.receiveAll(1L));
    }

    @Test
    public void shouldSuccessReceive() {

        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdInventarioFilme(1L);
        inventarioFilme.setIdUsuario(1L);
        inventarioFilme.setIdFilme(1L);

        when(inventarioFilmeRepository.findByIdUsuarioAndIdFilme(1L, 1L)).thenReturn(inventarioFilme);

        service.receive(1L, 1L);

        verify(inventarioFilmeRepository, times(1)).save(inventarioFilme);
    }

    @Test
    public void shouldFailReceive() {

        when(inventarioFilmeRepository.findByIdUsuarioAndIdFilme(1L, 1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> service.receive(1L, 1L));
    }

    @Test
    public void shouldSuccessDeleteById() {

        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdInventarioFilme(1L);
        inventarioFilme.setIdFilme(1L);

        when(inventarioFilmeRepository.findById(1L)).thenReturn(Optional.of(inventarioFilme));

        service.deleteById(1L);

        verify(inventarioFilmeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldFailDeleteByIdWhenRented() {

        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdInventarioFilme(1L);
        inventarioFilme.setIdFilme(1L);
        inventarioFilme.setIdUsuario(1L);

        when(inventarioFilmeRepository.findById(1L)).thenReturn(Optional.of(inventarioFilme));

        assertThrows(RuntimeException.class, () -> service.deleteById(1L));
    }

    @Test
    public void shouldFailDeleteByIdWhenDoesNotExist() {

        when(inventarioFilmeRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> service.deleteById(1L));
    }
}