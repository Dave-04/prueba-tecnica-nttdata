package com.microservice.usuarioexterno.services;

import com.microservice.usuarioexterno.model.Cliente;
import com.microservice.usuarioexterno.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        cliente=new Cliente();
        cliente.setEstado(true);
        cliente.setNombre("David Posso");
        cliente.setIdentificacion("1715709414");
        cliente.setTelefono("0960138940");
    }

    @Test
    void obtenerClientePorId() {
        Long clienteId = 1L;
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.ofNullable(cliente));
        assertNotNull(clienteService.obtenerClientePorId(clienteId));
    }
}