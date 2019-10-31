package com.twl.apilocadora.repository;

import com.twl.apilocadora.model.InventarioFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioFilmeRepository extends JpaRepository<InventarioFilme, Long> {

    Integer countByIdFilme(Long idFilme);

    Integer countByIdFilmeAndIdUsuarioIsNull(Long idFilme);

    List<InventarioFilme> findAllByIdFilme(Long idFilme);

    InventarioFilme findFirstByIdFilmeAndIdUsuarioIsNull(Long idFilme);
}
