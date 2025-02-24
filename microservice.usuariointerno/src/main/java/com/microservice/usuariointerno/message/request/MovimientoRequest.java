package com.microservice.usuariointerno.message.request;

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
public class MovimientoRequest {
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Long cuentaId;
}
