package com.microservice.usuariointerno.services;

import com.microservice.usuariointerno.dto.ReporteEstadoCuenta;
import com.microservice.usuariointerno.dto.ReporteRequest;

import java.time.LocalDate;

public interface ReporteServices {

    public ReporteEstadoCuenta generarReporteEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);

}
