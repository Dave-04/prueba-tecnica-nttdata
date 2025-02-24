package com.microservice.usuarioexterno.services;

import com.microservice.usuarioexterno.message.response.CuentaByClienteResponse;
import com.microservice.usuarioexterno.model.Cliente;

public interface ClienteService {

    Cliente obtenerClientePorId(Long id);

    Cliente saveCliente(Cliente cliente);

    public Cliente actualizarCliente(Long clienteId, Cliente cliente);

    public void eliminarCliente(Long clienteId);

    CuentaByClienteResponse findCuentaByIdCliente(Long clienteId);
}
