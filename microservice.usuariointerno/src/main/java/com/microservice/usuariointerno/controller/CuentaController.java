package com.microservice.usuariointerno.controller;

import com.microservice.usuariointerno.model.Cuenta;
import com.microservice.usuariointerno.services.CuentaServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaServices cuentaServices;

    // Endpoint para obtener un cliente por su ID
    @GetMapping("/{id}")
    public Cuenta obtenerCuentaPorId(@PathVariable Long id) {

        return cuentaServices.obtenerCuentaPorId(id);
    }

    @GetMapping("/searchByCliente/{idCliente}")
    public ResponseEntity<?> finByIdCliente(@PathVariable Long idCliente) {
        //log.info("id {}", idCliente);
        List<Cuenta> cuentas = cuentaServices.obtenerCuentaPorIdCliente(idCliente);

        if (cuentas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron cuentas para el cliente con ID: " + idCliente);
        }

        return ResponseEntity.ok(cuentas);
    }

    @PostMapping("/createCuenta")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cuenta> saveCuenta(@RequestBody Cuenta cuenta) {

        Cuenta crearCuenta = cuentaServices.saveCuenta(cuenta);
        return crearCuenta != null ? ResponseEntity.ok(crearCuenta) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Cuenta updatedCuenta = cuentaServices.actualizarCuenta(id, cuenta);
        return updatedCuenta != null ? ResponseEntity.ok(updatedCuenta) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaServices.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }

}
