package com.twl.apilocadora.repository;

import com.twl.apilocadora.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Set<Filme> findAllByIdFilmeOrTituloContainingIgnoreCaseOrDiretorContainingIgnoreCase(Long idFilme, String titulo, String diretor);

//    @Query("SELECT f"
//            + " FROM Filme f WHERE ((:idFilme IS NULL) OR (:idFilme IS NOT NULL AND f.idFilme = :idFilme))"
//            +" AND ((:titulo IS NULL) OR (:idFilme IS NOT NULL AND UPPER(f.titulo) LIKE UPPER('%:titulo%')))"
//            +" AND ((:diretor IS NULL) OR (:diretor IS NOT NULL AND UPPER(f.diretor) LIKE UPPER('%:diretor%')))")
//    Set<Filme> findBy(@Param("idFilme") Long idFilme,
//                      @Param("titulo") String titulo,
//                      @Param("diretor") String diretor);
}
