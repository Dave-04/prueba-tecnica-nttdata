package com.microservice.usuariointerno.services;

import com.microservice.usuariointerno.message.request.MovimientoRequest;
import com.microservice.usuariointerno.message.response.SaldoInsuficienteResponse;
import com.microservice.usuariointerno.model.Cuenta;
import com.microservice.usuariointerno.model.Movimiento;
import com.microservice.usuariointerno.repository.CuentaRepository;
import com.microservice.usuariointerno.repository.MovimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.time.ZoneId;
import java.util.Date;

@Service
public class MovimientoServicesImpl implements MovimientoServices {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    // Obtener un cliente por su ID
    @Override
    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    @Override
    public Movimiento saveMovimiento(MovimientoRequest movimientoRequest) {

        Movimiento movimiento= new Movimiento();
        movimiento.setFecha(movimientoRequest.getFecha());
        movimiento.setTipoMovimiento(movimientoRequest.getTipoMovimiento());
        movimiento.setValor(movimientoRequest.getValor());
        movimiento.setSaldo(movimientoRequest.getSaldo());
        // Buscar la cuenta por su ID
        Cuenta cuenta = cuentaRepository.findById(movimientoRequest.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        movimiento.setCuenta(cuenta);

       return movimientoRepository.save(movimiento);
    }

    @Override
    public Movimiento actualizarMovimiento(Long movimientoId, MovimientoRequest movimientoRequest) {
        if (movimientoRepository.existsById(movimientoId)) {

            Movimiento movimiento= new Movimiento();
            movimiento.setFecha(movimientoRequest.getFecha());
            movimiento.setTipoMovimiento(movimientoRequest.getTipoMovimiento());
            movimiento.setValor(movimientoRequest.getValor());
            movimiento.setSaldo(movimientoRequest.getSaldo());
            // Buscar la cuenta por su ID
            Cuenta cuenta = cuentaRepository.findById(movimientoRequest.getCuentaId())
                    .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
            movimiento.setCuenta(cuenta);
            return movimientoRepository.save(movimiento);
        }
        return null;
    }

    @Override
    public void eliminarMovimiento(Long movimientoId) {
        movimientoRepository.deleteById(movimientoId);
    }

    @Transactional
    public Movimiento registrarMovimiento(MovimientoRequest movimientoRequest){
        // Obtener la fecha actual como LocalDate
        LocalDate localDate = LocalDate.now();

        // Convertir LocalDate a Date
        //LocalDateTime fecha = LocalDateTime.parse(localDate.toString());

        movimientoRequest.setValor(Math.abs(movimientoRequest.getValor()));

        // Buscar la cuenta
        Cuenta cuenta = cuentaRepository.findById(movimientoRequest.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        //Validar tipo movimiento
        if(!movimientoRequest.getTipoMovimiento().equals("Retiro") && !movimientoRequest.getTipoMovimiento().equals("Deposito")){
            throw new SaldoInsuficienteResponse("Movimiento no es retiro ni deposito");
        }

        // Validar saldo para retiros
        if (movimientoRequest.getTipoMovimiento().equalsIgnoreCase("Retiro") && cuenta.getSaldoInicial() - movimientoRequest.getValor() < 0) {
            throw new SaldoInsuficienteResponse("Saldo no disponible");
        }

        // Actualizar el saldo de la cuenta
        double nuevoSaldo;
        if(movimientoRequest.getTipoMovimiento().equalsIgnoreCase("Retiro")){
            nuevoSaldo = cuenta.getSaldoInicial() - movimientoRequest.getValor();
        }else{
            nuevoSaldo = cuenta.getSaldoInicial() + movimientoRequest.getValor();
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        // Crear y guardar el movimiento
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(localDate);
        movimiento.setTipoMovimiento(movimientoRequest.getTipoMovimiento());
        movimiento.setValor(movimientoRequest.getValor());
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);

        return movimientoRepository.save(movimiento);

    }
}
