package com.microservice.usuariointerno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEstadoCuenta {
    private String nombre;
    private String identificacion;
    private String telefono;
    private List<InfoCuenta> infoCuentas;

}
