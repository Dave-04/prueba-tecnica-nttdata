package com.microservice.services;

import com.microservice.entities.Cuenta;
import com.microservice.persistence.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServicesImpl implements CuentaServices {

    @Autowired
    private CuentaRepository cuentaRepository;

    // Obtener un cliente por su ID
    @Override
    public List<Cuenta> obtenerCuentaPorIdCliente(Long id) {

        return cuentaRepository.obtenerCuentasPorIdCliente(id);
    }

    @Override
    public void saveCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }
}
