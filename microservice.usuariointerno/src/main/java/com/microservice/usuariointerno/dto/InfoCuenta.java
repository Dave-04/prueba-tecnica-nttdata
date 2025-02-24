package com.microservice.usuariointerno.dto;

import com.microservice.usuariointerno.model.Movimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoCuenta {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private List<Movimiento> listMovimiento;
}
