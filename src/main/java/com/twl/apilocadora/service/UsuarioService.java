package com.twl.apilocadora.service;

import com.twl.apilocadora.dto.LoginDto;
import com.twl.apilocadora.model.Usuario;

import java.util.Set;

public interface UsuarioService extends CrudService<Usuario, Long> {

    Set<Usuario> findBy(Long idUsuario, String nomeCompleto, String email);

    Boolean authenticate(LoginDto loginDto);
}
