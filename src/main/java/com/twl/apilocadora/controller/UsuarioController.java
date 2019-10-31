package com.twl.apilocadora.controller;

import com.twl.apilocadora.dto.LoginDto;
import com.twl.apilocadora.exceptions.BusinessException;
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
        return ResponseEntity.ok(service.save(usuario));
    }

    @GetMapping
    public Set findAll() {
        return service.findAll();
    }

    @GetMapping("/filtro")
    public ResponseEntity findByFilter(@RequestParam(required = false) Long idUsuario,
                            @RequestParam(required = false) String nomeCompleto,
                            @RequestParam(required = false) String email) {

        Set<Usuario> usuarios = service.findBy(idUsuario, nomeCompleto, email);

        return CollectionUtils.isEmpty(usuarios)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(usuarios);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long idUsuario) throws BusinessException {

        service.deleteById(idUsuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody LoginDto loginDto) {

        return service.authenticate(loginDto)
                ? ResponseEntity.ok("Login permitido!")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválida!");
    }
}
