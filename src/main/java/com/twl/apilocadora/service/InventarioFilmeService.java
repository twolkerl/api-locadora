package com.twl.apilocadora.service;

import com.twl.apilocadora.model.InventarioFilme;

import java.util.List;

public interface InventarioFilmeService extends CrudService<InventarioFilme, Long> {

    Integer countByIdFilme(Long idFilme);

    Integer countAvailableByIdFilme(Long idFilme);

    List<InventarioFilme> findAllByIdFilme(Long idFilme);
}
