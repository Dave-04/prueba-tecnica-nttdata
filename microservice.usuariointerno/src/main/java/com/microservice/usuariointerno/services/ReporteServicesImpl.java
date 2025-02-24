package com.microservice.usuariointerno.services;

import com.microservice.usuariointerno.dto.InfoCuenta;
import com.microservice.usuariointerno.dto.ReporteCliente;
import com.microservice.usuariointerno.dto.ReporteEstadoCuenta;
import com.microservice.usuariointerno.dto.ReporteRequest;
import com.microservice.usuariointerno.model.Cuenta;
import com.microservice.usuariointerno.model.Movimiento;
import com.microservice.usuariointerno.repository.CuentaRepository;
import com.microservice.usuariointerno.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteServicesImpl implements ReporteServices{
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CLIENTE_SERVICE_URL = "http://localhost:9090/clientes";

    @Override
    public ReporteEstadoCuenta generarReporteEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin){
        // Obtener las cuentas del cliente desde el servicio de Cliente
//        long clienteId = reporteRequest.getClienteId();
//
//        LocalDate dateI = reporteRequest.getFechaInicio();
//        LocalDate dateF = reporteRequest.getFechaFin();

        ReporteCliente cliente = restTemplate.getForObject(
                CLIENTE_SERVICE_URL + "/{clienteId}",
                ReporteCliente.class,
                clienteId
        );
        if(cliente==null){
            throw new IllegalArgumentException("El cliente con ID " + clienteId + " no tiene información válida.");
        }

        List<Cuenta> cuentaList= cuentaRepository.obtenerCuentasPorIdCliente(clienteId);

        if(cuentaList==null || cuentaList.isEmpty()){
            throw new IllegalArgumentException("El cliente con ID " + clienteId + " no tiene cuentas válida.");
        }
        List<InfoCuenta> infoCuentaList = new ArrayList<>();

        cuentaList.forEach(cuenta -> {
            InfoCuenta infoCuenta = new InfoCuenta(); // Crear un nuevo objeto InfoCuenta por cada cuenta

            infoCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
            infoCuenta.setTipoCuenta(cuenta.getTipoCuenta());
            infoCuenta.setSaldoInicial(cuenta.getSaldoInicial());

            // Obtener los movimientos asociados a la cuenta
            List<Movimiento> movimientoList =
                    movimientoRepository.obtenerMovimientosPorIdCuenta(
                            cuenta.getCuentaId(),fechaInicio,fechaFin);

            // Si la lista de movimientos no está vacía ni nula, asignarla
            if (movimientoList != null && !movimientoList.isEmpty()) {
                infoCuenta.setListMovimiento(movimientoList);
            }

            // Agregar el objeto InfoCuenta a la lista
            infoCuentaList.add(infoCuenta);
        });

        ReporteEstadoCuenta reporteEstadoCuenta = new ReporteEstadoCuenta();
        reporteEstadoCuenta.setNombre(cliente.getNombre());
        reporteEstadoCuenta.setIdentificacion(cliente.getIdentificacion());
        reporteEstadoCuenta.setTelefono(cliente.getTelefono());
        reporteEstadoCuenta.setInfoCuentas(infoCuentaList);

        return reporteEstadoCuenta;
    }
}
