package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.model.Usuario;
import com.twl.apilocadora.repository.UsuarioRepository;
import com.twl.apilocadora.service.UsuarioService;
import com.twl.apilocadora.util.EncryptUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
}
