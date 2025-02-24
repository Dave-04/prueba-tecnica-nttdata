package com.microservice.usuariointerno.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "cuentas")
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private long cuentaId;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    private Boolean estado;

    @Column(name = "cliente_id")
    private long clienteId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Movimiento> movimientos;

}
