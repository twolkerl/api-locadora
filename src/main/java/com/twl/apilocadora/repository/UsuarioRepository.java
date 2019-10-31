package com.twl.apilocadora.repository;

import com.twl.apilocadora.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//    Set<Usuario> findByNomeCompletoOrEmailOrIdUsuario(String nomeCompleto, String email, Long idUsuario);
}
