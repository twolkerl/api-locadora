package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.repository.InventarioFilmeRepository;
import com.twl.apilocadora.service.InventarioFilmeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioFilmeServiceImpl extends CrudServiceImpl<InventarioFilme, Long> implements InventarioFilmeService {

    public InventarioFilmeServiceImpl(InventarioFilmeRepository repository) {
        super(repository);
    }

    @Override
    protected InventarioFilmeRepository getRepository() {
        return (InventarioFilmeRepository) super.getRepository();
    }

    @Override
    public Integer countByIdFilme(Long idFilme) {
        return getRepository().countByIdFilme(idFilme);
    }

    @Override
    public Integer countAvailableByIdFilme(Long idFilme) {
        return getRepository().countByIdFilmeAndIdUsuarioIsNull(idFilme);
    }

    @Override
    public List<InventarioFilme> findAllByIdFilme(Long idFilme) {
        return getRepository().findAllByIdFilme(idFilme);
    }
}
