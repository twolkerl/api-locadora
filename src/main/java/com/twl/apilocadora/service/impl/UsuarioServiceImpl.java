package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.exceptions.BusinessException;
import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.model.Usuario;
import com.twl.apilocadora.repository.InventarioFilmeRepository;
import com.twl.apilocadora.repository.UsuarioRepository;
import com.twl.apilocadora.service.UsuarioService;
import com.twl.apilocadora.util.EncryptUtils;
import com.twl.apilocadora.util.MatcherUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long> implements UsuarioService {

    private final InventarioFilmeRepository inventarioFilmeRepository;

    public UsuarioServiceImpl(UsuarioRepository repository, InventarioFilmeRepository inventarioFilmeRepository) {
        super(repository);
        this.inventarioFilmeRepository = inventarioFilmeRepository;
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
    public Set<Usuario> findBy(Long idUsuario, String nomeCompleto, String email) {

        Usuario usuario = Usuario.builder()
                .idUsuario(idUsuario)
                .nomeCompleto(nomeCompleto)
                .email(email)
                .build();

        return new HashSet<>(getRepository().findAll(Example.of(usuario, MatcherUtils.matchAnyContainingIgnoreCase())));
    }

    @Override
    public void deleteById(Long idUsuario) throws BusinessException {

        List<InventarioFilme> inventarioFilmeList = inventarioFilmeRepository.findAllByIdUsuario(idUsuario);

        if (CollectionUtils.isEmpty(inventarioFilmeList)) {
            super.deleteById(idUsuario);
        } else {
            throw new BusinessException("O usuário não pode ser excluído pois está com um filme alugado!");
        }
    }
}
