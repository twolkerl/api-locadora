package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.model.Filme;
import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.repository.FilmeRepository;
import com.twl.apilocadora.repository.InventarioFilmeRepository;
import com.twl.apilocadora.service.FilmeService;
import com.twl.apilocadora.util.MatcherUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilmeServiceImpl extends CrudServiceImpl<Filme, Long> implements FilmeService {

    private final InventarioFilmeRepository inventarioFilmeRepository;

    public FilmeServiceImpl(FilmeRepository repository, InventarioFilmeRepository inventarioFilmeRepository) {
        super(repository);
        this.inventarioFilmeRepository = inventarioFilmeRepository;
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

    @Override
    public void deleteById(Long idFilme) {

        List<InventarioFilme> inventarioFilmeList = inventarioFilmeRepository.findAllByIdFilme(idFilme);

        if (CollectionUtils.isEmpty(inventarioFilmeList)) {
            super.deleteById(idFilme);
        } else {
            throw new RuntimeException("O filme não pode ser excluído pois ainda existem cópias em estoque!");
        }
    }
}
