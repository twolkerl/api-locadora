package com.twl.apilocadora.service;

import com.twl.apilocadora.model.Filme;

import java.util.Set;

public interface FilmeService extends CrudService<Filme, Long> {

    Set<Filme> findBy(Long idFilme, String titulo, String diretor);
}
