package com.microservice.usuariointerno.controller;

import com.microservice.usuariointerno.dto.ReporteEstadoCuenta;
import com.microservice.usuariointerno.dto.ReporteRequest;
import com.microservice.usuariointerno.message.request.MovimientoRequest;
import com.microservice.usuariointerno.model.Movimiento;
import com.microservice.usuariointerno.services.ReporteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
public class Reportecontroller {

    @Autowired
    private ReporteServices reporteServices;

    @GetMapping("/estadoCuenta")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReporteEstadoCuenta> estadoCuenta( @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
                                                             @RequestParam Long clienteId) {

        ReporteEstadoCuenta reporteEstadoCuenta = reporteServices.generarReporteEstadoCuenta(clienteId, fechaInicio, fechaFin);
        return reporteEstadoCuenta != null ? ResponseEntity.ok(reporteEstadoCuenta) : ResponseEntity.notFound().build();
    }
}
