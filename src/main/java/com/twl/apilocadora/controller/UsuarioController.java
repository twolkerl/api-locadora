package com.twl.apilocadora.controller;

import com.twl.apilocadora.model.Usuario;
import com.twl.apilocadora.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping
    public Set findAll() {
        return service.findAll();
    }

    @GetMapping("/filter")
    public ResponseEntity findByFilter(@RequestParam(required = false) Long idUsuario,
                            @RequestParam(required = false) String nomeCompleto,
                            @RequestParam(required = false) String email) {

        Set<Usuario> usuarios = service.findBy(idUsuario, nomeCompleto, email);

        return CollectionUtils.isEmpty(usuarios)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(usuarios);
    }
}
