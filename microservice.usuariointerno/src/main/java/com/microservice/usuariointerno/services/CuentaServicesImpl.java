package com.microservice.usuariointerno.services;

import com.microservice.usuariointerno.model.Cuenta;
import com.microservice.usuariointerno.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaServicesImpl implements CuentaServices {

    @Autowired
    private CuentaRepository cuentaRepository;

    // Obtener un cuenta por su ID
    @Override
    public Cuenta obtenerCuentaPorId(Long id){

        return cuentaRepository.findById(id).orElse(null);
    }

    @Override
    public Cuenta actualizarCuenta(Long cuentaId, Cuenta cuenta) {
        if (cuentaRepository.existsById(cuentaId)) {
            cuenta.setCuentaId(cuentaId);
            return cuentaRepository.save(cuenta);
        }
        return null;
    }

    @Override
    public void eliminarCuenta(Long cuentaId) {
        cuentaRepository.deleteById(cuentaId);
    }

    @Override
    public List<Cuenta> obtenerCuentaPorIdCliente(Long id) {

        return cuentaRepository.obtenerCuentasPorIdCliente(id);

    }

    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
}
