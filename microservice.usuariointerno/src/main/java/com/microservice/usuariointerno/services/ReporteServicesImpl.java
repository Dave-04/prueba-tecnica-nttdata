package com.microservice.usuariointerno.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.usuariointerno.dto.InfoCuenta;
import com.microservice.usuariointerno.dto.ReporteCliente;
import com.microservice.usuariointerno.dto.ReporteEstadoCuenta;
import com.microservice.usuariointerno.dto.ReporteRequest;
import com.microservice.usuariointerno.model.Cuenta;
import com.microservice.usuariointerno.model.Movimiento;
import com.microservice.usuariointerno.repository.CuentaRepository;
import com.microservice.usuariointerno.repository.MovimientoRepository;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // Crear un Logger para esta clase
    private static final Logger logger = LoggerFactory.getLogger(ReporteServicesImpl.class);

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CLIENTE_SERVICE_URL = "http://localhost:9090/api/clientes";

    @Override
    public ReporteEstadoCuenta generarReporteEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin){
        // Obtener las cuentas del cliente desde el servicio de Cliente

        // Realizar la solicitud GET con Unirest
        HttpResponse<JsonNode> response = Unirest.get(CLIENTE_SERVICE_URL + "/{clienteId}")
                .routeParam("clienteId", String.valueOf(clienteId))
                .header("Accept", "application/json")
                .asJson();

        logger.info("jason  {}",response.getBody().toString());
        ReporteCliente cliente=null;

        // Verificar el estado de la respuesta (200 OK)
        if (response.getStatus() == 200) {
            // Convertir la respuesta JSON a un objeto Cliente
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                cliente = objectMapper.readValue(response.getBody().toString(), ReporteCliente.class);
            } catch (Exception e) {
                System.out.println("Error al deserializar la respuesta: " + e.getMessage());
            }
        } else {
            // Si la respuesta no es exitosa, puedes manejar el error aquí
            System.out.println("Error en la solicitud. Código de estado: " + response.getStatus());
        }


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
