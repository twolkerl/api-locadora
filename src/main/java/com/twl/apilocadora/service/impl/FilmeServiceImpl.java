package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.filter.FilmeFilter;
import com.twl.apilocadora.model.Filme;
import com.twl.apilocadora.repository.FilmeRepository;
import com.twl.apilocadora.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FilmeServiceImpl extends CrudServiceImpl<Filme, Long> implements FilmeService {

    public FilmeServiceImpl(FilmeRepository repository) {
        super(repository);
    }

    @Override
    protected FilmeRepository getRepository() {
        return (FilmeRepository) super.getRepository();
    }

    @Override
    public Set<Filme> findByFilter(FilmeFilter filter) {
        return new HashSet<>(getRepository().findAllByIdFilmeOrTituloLikeOrDiretorLike(filter.getIdFilme(), filter.getTitulo(), filter.getAutor()));
    }
}
