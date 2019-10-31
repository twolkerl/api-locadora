package com.twl.apilocadora.service;

import com.twl.apilocadora.filter.UsuarioFilter;
import com.twl.apilocadora.model.Usuario;

import java.util.Set;

public interface UsuarioService extends CrudService<Usuario, Long> {

    Set<Usuario> findByFilter(UsuarioFilter filter);
}
