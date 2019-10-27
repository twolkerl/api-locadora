package com.twl.apilocadora.repository;

import com.twl.apilocadora.model.InventarioFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioFilmeRepository extends JpaRepository<InventarioFilme, Integer> {
}
