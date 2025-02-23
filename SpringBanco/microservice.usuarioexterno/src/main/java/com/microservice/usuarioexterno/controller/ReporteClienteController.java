package com.microservice.controller;

import com.microservice.entities.Cliente;
import com.microservice.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ReporteClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoint para obtener un cliente por su ID
    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id);
    }

    @PostMapping("/createdCliente")
    @ResponseStatus(HttpStatus.CREATED)
    public void crearCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
    }

    @GetMapping("/serach-cuenta/{clienteId}")
    public ResponseEntity<?> findCuentaByIdClient(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.findCuentaByIdCliente(clienteId));
    }
}
