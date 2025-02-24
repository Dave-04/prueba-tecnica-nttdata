package com.microservice.usuariointerno.repository;

import com.microservice.usuariointerno.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query("SELECT c FROM Cuenta c WHERE c.clienteId = ?1 order by c.cuentaId desc")
    List<Cuenta> obtenerCuentasPorIdCliente(Long id);

}
