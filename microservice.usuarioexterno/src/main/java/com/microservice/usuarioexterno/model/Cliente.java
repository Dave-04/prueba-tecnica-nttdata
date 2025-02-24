package com.microservice.usuarioexterno.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona{

    private String password;
    private Boolean estado;


    public Cliente(long l, String juanPérez, String masculino, int i, String number, String s, String s1) {
    }
}
