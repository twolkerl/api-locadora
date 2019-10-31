package com.twl.apilocadora.controller;

import com.twl.apilocadora.model.Usuario;
import com.twl.apilocadora.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

            Usuario savedUsuario = service.save(usuario);

            // Retorna apenas o ID do usuário criado.
            return ResponseEntity.ok(savedUsuario.getIdUsuario());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
