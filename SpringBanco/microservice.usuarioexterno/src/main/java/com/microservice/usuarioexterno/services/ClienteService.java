package com.microservice.services;


import com.microservice.entities.Cliente;
import com.microservice.message.response.CuentaByClienteResponse;

public interface ClienteService {

    Cliente obtenerClientePorId(Long id);

    void saveCliente(Cliente cliente);

    CuentaByClienteResponse findCuentaByIdCliente(Long clienteId);
}
