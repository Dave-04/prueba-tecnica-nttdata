package com.microservice.services;

import com.microservice.entities.Cuenta;

import java.util.List;

public interface CuentaServices {

    List<Cuenta> obtenerCuentaPorIdCliente(Long id);

    void saveCuenta(Cuenta cuenta);
}
