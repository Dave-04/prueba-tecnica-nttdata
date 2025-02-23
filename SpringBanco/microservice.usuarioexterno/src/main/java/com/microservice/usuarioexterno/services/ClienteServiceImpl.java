package com.microservice.services;

import com.microservice.dto.CuentaDTO;
import com.microservice.entities.Cliente;
import com.microservice.feignclient.UsuarioInternoClient;
import com.microservice.message.response.CuentaByClienteResponse;
import com.microservice.persistence.ClienteRepository;
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
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
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
