package com.twl.apilocadora.service;

import com.twl.apilocadora.dto.LocacaoDto;
import com.twl.apilocadora.model.InventarioFilme;

import java.util.List;

public interface InventarioFilmeService extends CrudService<InventarioFilme, Long> {

    Integer countByIdFilme(Long idFilme);

    Integer countAvailableByIdFilme(Long idFilme);

    List<InventarioFilme> findAllByIdFilme(Long idFilme);

    List<InventarioFilme> rent(LocacaoDto locacaoDto);

    void receiveAll(Long idUsuario);

    void receive(Long idUsuario, Long idFilme);
}
