package com.juanjo.proyetofinal.controladores.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    // Correo del usuario a autenticar.
    private String email;

    // Contraseña del usuario a autenticar.
    private String password;
}
