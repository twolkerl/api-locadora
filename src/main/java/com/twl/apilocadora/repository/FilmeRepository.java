package com.twl.apilocadora.repository;

import com.twl.apilocadora.filter.FilmeFilter;
import com.twl.apilocadora.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findAllByIdFilmeOrTituloLikeOrDiretorLike(Long idFilme, String titulo, String diretor);
}
