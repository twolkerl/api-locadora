package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.filter.UsuarioFilter;
import com.twl.apilocadora.model.Usuario;
import com.twl.apilocadora.repository.UsuarioRepository;
import com.twl.apilocadora.service.UsuarioService;
import com.twl.apilocadora.util.EncryptUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long> implements UsuarioService {

    public UsuarioServiceImpl(UsuarioRepository repository) {
        super(repository);
    }

    @Override
    protected UsuarioRepository getRepository() {
        return (UsuarioRepository) super.getRepository();
    }

    @Override
    public Usuario save(Usuario usuario) {

        usuario.setSenha(EncryptUtils.encryptPassword(usuario.getSenha()));

        return super.save(usuario);
    }

    @Override
    public Set<Usuario> findByFilter(UsuarioFilter filter) {

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(filter.getIdUsuario());
        usuario.setNomeCompleto(filter.getNomeCompleto());
        usuario.setEmail(filter.getEmail());

        return new HashSet<>(getRepository().findAll(Example.of(usuario)));
    }
}
