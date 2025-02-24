package com.microservice.usuarioexterno.controller;

import com.microservice.usuarioexterno.model.Cliente;
import com.microservice.usuarioexterno.services.ClienteService;
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
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente crearCliente = clienteService.saveCliente(cliente);
        return crearCliente != null ? ResponseEntity.ok(crearCliente) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.actualizarCliente(id, cliente);
        return updatedCliente != null ? ResponseEntity.ok(updatedCliente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/serach-cuenta/{clienteId}")
    public ResponseEntity<?> findCuentaByIdClient(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.findCuentaByIdCliente(clienteId));
    }

}
