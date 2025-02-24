package com.microservice.usuariointerno.services;

import com.microservice.usuariointerno.model.Cuenta;
import java.util.List;

public interface CuentaServices {

    public Cuenta obtenerCuentaPorId(Long id);

    List<Cuenta> obtenerCuentaPorIdCliente(Long id);

    Cuenta saveCuenta(Cuenta cuenta);

    public Cuenta actualizarCuenta(Long cuentaId, Cuenta cuenta);

    public void eliminarCuenta(Long cuentaId);
}
