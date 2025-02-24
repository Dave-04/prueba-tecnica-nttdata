package com.microservice.usuariointerno.services;

import com.microservice.usuariointerno.message.request.MovimientoRequest;
import com.microservice.usuariointerno.model.Movimiento;

public interface MovimientoServices {

    Movimiento obtenerMovimientoPorId(Long id);

    public Movimiento saveMovimiento(MovimientoRequest movimientoRequest);

    public Movimiento actualizarMovimiento(Long movimientoId, MovimientoRequest movimientoRequest);

    public void eliminarMovimiento(Long movimientoId);

    public Movimiento registrarMovimiento(MovimientoRequest movimientoRequest);
}

