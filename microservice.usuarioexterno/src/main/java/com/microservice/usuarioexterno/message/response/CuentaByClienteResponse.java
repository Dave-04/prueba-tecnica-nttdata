package com.microservice.usuarioexterno.message.response;


import com.microservice.usuarioexterno.dto.CuentaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaByClienteResponse {

    private String nombre;
    private String identificacion;
    private String telefono;
    private Boolean estado;
    private List<CuentaDTO> cuentaDTOList;

}
