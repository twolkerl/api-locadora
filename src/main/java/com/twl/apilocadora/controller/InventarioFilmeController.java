package com.twl.apilocadora.controller;

import com.twl.apilocadora.dto.LocacaoDto;
import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.service.InventarioFilmeService;
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
        return ResponseEntity.ok(service.save(inventarioFilme));
    }

    @GetMapping("/{idFilme}")
    public ResponseEntity findByFilme(@PathVariable Long idFilme) {

        List<InventarioFilme> inventarioFilmeList = service.findAllByIdFilme(idFilme);

        return CollectionUtils.isEmpty(inventarioFilmeList)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(inventarioFilmeList);
    }

    @PutMapping("/alugar")
    public ResponseEntity rent(@RequestBody LocacaoDto locacaoDto) {
        return ResponseEntity.ok(service.rent(locacaoDto));
    }

    @PutMapping("/receber-todos")
    public ResponseEntity receiveAll(@RequestParam Long idUsuario) {

        service.receiveAll(idUsuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/receber")
    public ResponseEntity receive(@RequestParam Long idUsuario,
                                  @RequestParam Long idFilme) {

        service.receive(idUsuario, idFilme);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/qtd-filme")
    public ResponseEntity countByFilme(@RequestParam Long idFilme) {
        return ResponseEntity.ok(service.countByIdFilme(idFilme));
    }

    @GetMapping("/qtd-filme-disponivel")
    public ResponseEntity countAvailableByFilme(@RequestParam Long idFilme){
        return ResponseEntity.ok(service.countAvailableByIdFilme(idFilme));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long idInventarioFilme) {

        service.deleteById(idInventarioFilme);
        return ResponseEntity.ok().build();
    }
}
