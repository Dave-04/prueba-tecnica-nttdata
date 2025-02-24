package com.microservice.usuarioexterno.feignclient;

import com.microservice.usuarioexterno.dto.CuentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "mscv-usuariointerno", url = "localhost:8090/api/cuentas")
public interface UsuarioInternoClient {

    @GetMapping("search-by-cliente/{idCliente}")
    List<CuentaDTO> findAllCuentasByCliente(@PathVariable Long clienteId);

}

