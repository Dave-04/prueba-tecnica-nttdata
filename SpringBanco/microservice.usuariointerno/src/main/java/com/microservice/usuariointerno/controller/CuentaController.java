package com.microservice.controller;

import com.microservice.entities.Cuenta;
import com.microservice.services.CuentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaServices cuentaServices;

    // Endpoint para obtener un cliente por su ID
//    @GetMapping("/{id}")
//    public Cuenta obtenerCuentaPorId(@PathVariable Long id) {
//        return cuentaServices.obtenerCuentaPorId(id);
//    }

    @GetMapping("search-by-cliente/{idCliente}")
    public ResponseEntity<?> finByIdCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(cuentaServices.obtenerCuentaPorIdCliente(idCliente));
    }

    @PostMapping("/createCuenta")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCuenta(@RequestBody Cuenta cuenta) {
        cuentaServices.saveCuenta(cuenta);
    }

}
