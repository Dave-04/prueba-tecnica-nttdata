package com.microservice.usuariointerno.repository;

import com.microservice.usuariointerno.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    //@Query("SELECT m FROM Movimiento m WHERE m.cuentaId = ?1 AND m.fecha BETWEEN ?2 AND ?3 ORDER BY m.movimientoId DESC")

    //List<Movimiento> obtenerMovimientosPorIdCuenta(Long id, LocalDate startDate, LocalDate endDate);
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.id = :cuentaId AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> obtenerMovimientosPorIdCuenta(
            @Param("cuentaId") Long cuentaId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );
}

