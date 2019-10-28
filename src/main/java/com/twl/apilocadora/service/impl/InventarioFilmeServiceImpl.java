package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.repository.InventarioFilmeRepository;
import com.twl.apilocadora.service.InventarioFilmeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InventarioFilmeServiceImpl extends CrudServiceImpl<InventarioFilme, Long> implements InventarioFilmeService {

    public InventarioFilmeServiceImpl(InventarioFilmeRepository repository) {
        super(repository);
    }

    @Override
    protected InventarioFilmeRepository getRepository() {
        return (InventarioFilmeRepository) super.getRepository();
    }
}
