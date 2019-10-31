package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.filter.FilmeFilter;
import com.twl.apilocadora.model.Filme;
import com.twl.apilocadora.repository.FilmeRepository;
import com.twl.apilocadora.service.FilmeService;
import com.twl.apilocadora.util.MatcherUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public Set<Filme> findBy(Long idFilme, String titulo, String diretor) {

        Filme filme = Filme.builder()
                .idFilme(idFilme)
                .titulo(titulo)
                .diretor(diretor)
                .build();

        return new HashSet<>(getRepository().findAll(Example.of(filme, MatcherUtils.matchAnyContainingIgnoreCase())));
    }
}
