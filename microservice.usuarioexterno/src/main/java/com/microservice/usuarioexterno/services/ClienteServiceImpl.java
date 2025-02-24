package com.microservice.usuarioexterno.services;

import com.microservice.usuarioexterno.dto.CuentaDTO;
import com.microservice.usuarioexterno.feignclient.UsuarioInternoClient;
import com.microservice.usuarioexterno.message.response.CuentaByClienteResponse;
import com.microservice.usuarioexterno.model.Cliente;
import com.microservice.usuarioexterno.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioInternoClient usuarioInternoClient;

    // Obtener un cliente por su ID
    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Long clienteId, Cliente cliente) {
        if (clienteRepository.existsById(clienteId)) {
            cliente.setId(clienteId);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    @Override
    public void eliminarCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    @Override
    public CuentaByClienteResponse findCuentaByIdCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(new Cliente());

        List<CuentaDTO> cuentaDTOList = usuarioInternoClient.findAllCuentasByCliente(clienteId);


        return CuentaByClienteResponse.builder()
                .nombre(cliente.getNombre())
                .identificacion(cliente.getIdentificacion())
                .telefono(cliente.getTelefono())
                .estado(cliente.getEstado())
                .cuentaDTOList(cuentaDTOList)
                .build();
    }
}
