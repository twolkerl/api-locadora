package com.twl.apilocadora.controller;

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

    @PostMapping
    public ResponseEntity save(@RequestBody Filme filme) {

        try {
            return ResponseEntity.ok(service.save(filme));
        } catch (Exception e) {
            // TODO tratar diferentes exceptions
            return ResponseEntity.badRequest().build();
        }
    }
}
