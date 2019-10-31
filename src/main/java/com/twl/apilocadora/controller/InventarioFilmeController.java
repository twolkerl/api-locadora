package com.twl.apilocadora.controller;

import com.twl.apilocadora.dto.LocacaoDto;
import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.service.InventarioFilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioFilmeController {

    private final InventarioFilmeService service;

    public InventarioFilmeController(InventarioFilmeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody InventarioFilme inventarioFilme) {

        try {
            return ResponseEntity.ok(service.save(inventarioFilme));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idFilme}")
    public ResponseEntity findByFilme(@PathVariable Long idFilme) {

        try {
            List<InventarioFilme> inventarioFilmeList = service.findAllByIdFilme(idFilme);

            return CollectionUtils.isEmpty(inventarioFilmeList)
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(inventarioFilmeList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping
    public ResponseEntity rent(@RequestBody LocacaoDto locacaoDto) {
        try {
            return ResponseEntity.ok(service.rent(locacaoDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping("/qtd-filme")
    public ResponseEntity countByFilme(@RequestParam("idFilme") Long idFilme) {
        try {
            return ResponseEntity.ok(service.countByIdFilme(idFilme));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping("/qtd-filme-disponivel")
    public ResponseEntity countAvailableByFilme(@RequestParam Long idFilme){
        try {
            return ResponseEntity.ok(service.countAvailableByIdFilme(idFilme));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
