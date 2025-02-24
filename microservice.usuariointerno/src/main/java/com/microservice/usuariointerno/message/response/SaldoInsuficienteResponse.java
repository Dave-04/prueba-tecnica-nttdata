package com.microservice.usuariointerno.message.response;

public class SaldoInsuficienteResponse extends RuntimeException {
    public SaldoInsuficienteResponse(String mensaje) {
        super(mensaje);
    }
}
