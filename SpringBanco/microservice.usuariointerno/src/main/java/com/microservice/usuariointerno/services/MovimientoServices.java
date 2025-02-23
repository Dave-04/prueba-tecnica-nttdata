package com.microservice.services;

import com.microservice.entities.Movimiento;

public interface MovimientoServices {
    Movimiento obtenerMovimientoPorId(Long id);
}

