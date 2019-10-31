package com.twl.apilocadora.controller;

import com.twl.apilocadora.exceptions.BusinessException;
import com.twl.apilocadora.model.Filme;
import com.twl.apilocadora.service.FilmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity findAll() {

        Set<Filme> filmes = service.findAll();

        return CollectionUtils.isEmpty(filmes)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(filmes);
    }

    @GetMapping("/filtro")
    public ResponseEntity findByFilter(@RequestParam(required = false) Long idFilme,
                                       @RequestParam(required = false) String titulo,
                                       @RequestParam(required = false) String diretor) {

        Set<Filme> filmes = service.findBy(idFilme, titulo, diretor);

        return CollectionUtils.isEmpty(filmes)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(filmes);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Filme filme) {
        return ResponseEntity.ok(service.save(filme));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long idFilme) throws BusinessException {

        service.deleteById(idFilme);
        return ResponseEntity.ok().build();
    }
}
