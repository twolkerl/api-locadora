package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.dto.LoginDto;
import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.model.Usuario;
import com.twl.apilocadora.repository.InventarioFilmeRepository;
import com.twl.apilocadora.repository.UsuarioRepository;
import com.twl.apilocadora.util.EncryptUtils;
import com.twl.apilocadora.util.MatcherUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private InventarioFilmeRepository inventarioFilmeRepository;

    @InjectMocks
    private UsuarioServiceImpl service;

    @BeforeEach
    void setUp() {
        initMocks(this);
        service = new UsuarioServiceImpl(usuarioRepository, inventarioFilmeRepository);
    }

    @Test
    public void shouldSuccessDeleteById() {
        when(inventarioFilmeRepository.findAllByIdUsuario(1L)).thenReturn(Collections.emptyList());

        service.deleteById(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldFailDeleteById() {
        InventarioFilme inventarioFilme = new InventarioFilme();
        inventarioFilme.setIdUsuario(1L);

        when(inventarioFilmeRepository.findAllByIdUsuario(1L)).thenReturn(Collections.singletonList(inventarioFilme));

        assertThrows(RuntimeException.class, () -> service.deleteById(1L));
    }

    @Test
    public void shouldSuccessAuthenticate() {
        Usuario usuario = getMockUser();

        when(usuarioRepository.findByEmail("teste@teste.com")).thenReturn(usuario);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("teste@teste.com");
        loginDto.setSenha("teste123");

        assertTrue(service.authenticate(loginDto));
    }

    @Test
    public void shouldFailAuthenticateWhenWrongPassword() {
        Usuario usuario = getMockUser();

        when(usuarioRepository.findByEmail("teste@teste.com")).thenReturn(usuario);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("teste@teste.com");
        loginDto.setSenha("testeFail");

        assertFalse(service.authenticate(loginDto));
    }

    @Test
    public void shouldFailAuthenticateWhenUserNotFound() {

        when(usuarioRepository.findByEmail("teste@mail.com")).thenReturn(null);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("teste@teste.com");
        loginDto.setSenha("testeFail");

        assertFalse(service.authenticate(loginDto));
    }

    @Test
    public void shouldSuccessFindBy() {

        Usuario usuarioExample = new Usuario();
        usuarioExample.setIdUsuario(1L);

        when(usuarioRepository.findAll(Example.of(usuarioExample, MatcherUtils.matchAnyContainingIgnoreCase())))
                .thenReturn(Collections.singletonList(getMockUser()));

        assertFalse(service.findBy(1L, null, null).isEmpty());
    }

    @Test
    public void shouldFailFindBy() {

        Usuario usuarioExample = new Usuario();
        usuarioExample.setIdUsuario(1L);

        when(usuarioRepository.findAll(Example.of(usuarioExample, MatcherUtils.matchAnyContainingIgnoreCase())))
                .thenReturn(Collections.emptyList());

        assertTrue(service.findBy(1L, null, null).isEmpty());
    }

    @Test
    public void shouldSuccessSave() {

        Usuario usuario = new Usuario(1L, "teste@teste.com", "teste", "teste123");

        when(usuarioRepository.save(usuario)).thenReturn(getMockUser());

        Usuario saved = service.save(usuario);

        assertEquals(saved.getIdUsuario(), getMockUser().getIdUsuario());
    }

    @Test
    public void shouldFailSave() {

        Usuario usuario = new Usuario(1L, null, "teste", "teste123");

        assertNull(service.save(usuario));
    }

    private Usuario getMockUser() {
        return new Usuario(1L, "teste@teste.com", "teste", EncryptUtils.encryptPassword("teste123"));
    }
}