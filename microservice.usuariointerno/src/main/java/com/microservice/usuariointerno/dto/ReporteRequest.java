package com.microservice.usuariointerno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteRequest {
    private long clienteId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
