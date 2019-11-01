package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.service.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class CrudServiceImpl<T, ID> implements CrudService<T, ID> {

    private final JpaRepository<T, ID> repository;

    protected CrudServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected JpaRepository<T, ID> getRepository() {
        return repository;
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public T save(T object) {
        return repository.save(object);
    }

    @Override
    public void delete(T object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
