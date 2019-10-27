package com.twl.apilocadora.controller;

import com.twl.apilocadora.service.InventarioFilmeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventario")
public class InventarioFilmeController {

    private final InventarioFilmeService service;

    public InventarioFilmeController(InventarioFilmeService service) {
        this.service = service;
    }
}
