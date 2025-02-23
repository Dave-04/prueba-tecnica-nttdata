package com.microservice.services;

import com.microservice.entities.Movimiento;
import com.microservice.persistence.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServicesImpl implements MovimientoServices {

    @Autowired
    private MovimientoRepository movimientoRepository;

    // Obtener un cliente por su ID
    @Override
    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }
}
