package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.model.Filme;
import com.twl.apilocadora.repository.FilmeRepository;
import com.twl.apilocadora.service.FilmeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmeServiceImpl extends CrudServiceImpl<Filme, Long> implements FilmeService {

    public FilmeServiceImpl(FilmeRepository repository) {
        super(repository);
    }

    @Override
    protected FilmeRepository getRepository() {
        return (FilmeRepository) super.getRepository();
    }
}
