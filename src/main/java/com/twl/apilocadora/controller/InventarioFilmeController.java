package com.twl.apilocadora.controller;

import com.twl.apilocadora.dto.LocacaoDto;
import com.twl.apilocadora.exceptions.BusinessException;
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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/alugar")
    public ResponseEntity rent(@RequestBody LocacaoDto locacaoDto) {
        try {
            return ResponseEntity.ok(service.rent(locacaoDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/receber-todos")
    public ResponseEntity receiveAll(@RequestParam Long idUsuario) {
        try {

            service.receiveAll(idUsuario);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/receber")
    public ResponseEntity receive(@RequestParam Long idUsuario,
                                  @RequestParam Long idFilme) {
        try {

            service.receive(idUsuario, idFilme);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/qtd-filme")
    public ResponseEntity countByFilme(@RequestParam Long idFilme) {
        try {
            return ResponseEntity.ok(service.countByIdFilme(idFilme));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/qtd-filme-disponivel")
    public ResponseEntity countAvailableByFilme(@RequestParam Long idFilme){
        try {
            return ResponseEntity.ok(service.countAvailableByIdFilme(idFilme));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long idInventarioFilme) {
        try {
            service.deleteById(idInventarioFilme);

            return ResponseEntity.ok().build();
        } catch (BusinessException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
