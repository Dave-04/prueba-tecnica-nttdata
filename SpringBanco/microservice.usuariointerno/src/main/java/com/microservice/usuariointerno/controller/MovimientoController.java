package com.microservice.controller;

import com.microservice.entities.Movimiento;
import com.microservice.services.MovimientoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoServices movimientoServices;

    // Endpoint para obtener un cliente por su ID
    @GetMapping("/{id}")
    public Movimiento obtenerMovimientoPorId(@PathVariable Long id) {
        return movimientoServices.obtenerMovimientoPorId(id);
    }
}
