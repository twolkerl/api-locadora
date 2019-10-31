package com.twl.apilocadora.controller;

import com.twl.apilocadora.model.Usuario;
import com.twl.apilocadora.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Usuario usuario) {
        try {

            return ResponseEntity.ok(service.save(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public Set findAll() {
        return service.findAll();
    }
}
