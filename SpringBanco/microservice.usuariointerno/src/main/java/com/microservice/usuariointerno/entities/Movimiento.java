package com.microservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "movimientos")
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private long movimientoId;

    private Date fecha;

    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    private Double valor;
    private Double saldo;

    @Column(name = "cuenta_id")
    private long cuentaId;
}
