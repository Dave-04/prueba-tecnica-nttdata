package com.microservice.usuariointerno.controller;

import com.microservice.usuariointerno.message.request.MovimientoRequest;
import com.microservice.usuariointerno.message.response.SaldoInsuficienteResponse;
import com.microservice.usuariointerno.model.Cuenta;
import com.microservice.usuariointerno.model.Movimiento;
import com.microservice.usuariointerno.services.MovimientoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoServices movimientoServices;

    // Endpoint para obtener un movimiento por su ID
    @GetMapping("/{id}")
    public Movimiento obtenerMovimientoPorId(@PathVariable Long id) {
        return movimientoServices.obtenerMovimientoPorId(id);
    }
    @PostMapping("/createMovimiento")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Movimiento> saveMovimiento(@RequestBody MovimientoRequest movimientoRequest) {

        Movimiento crearMovimiento = movimientoServices.saveMovimiento(movimientoRequest);
        return crearMovimiento != null ? ResponseEntity.ok(crearMovimiento) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable Long id, @RequestBody MovimientoRequest movimientoRequest) {
        Movimiento updatedMovimiento = movimientoServices.actualizarMovimiento(id, movimientoRequest);
        return updatedMovimiento != null ? ResponseEntity.ok(updatedMovimiento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        movimientoServices.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registrarMovimiento")
    public ResponseEntity<?> registrarMovimiento(@RequestBody MovimientoRequest movimientoRequest) {

        try {
            Movimiento movimiento = movimientoServices.registrarMovimiento(movimientoRequest);
            return ResponseEntity.ok(movimiento);
        } catch (SaldoInsuficienteResponse e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
