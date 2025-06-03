package com.juanjo.proyetofinal.controladores.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    // Nombre del usuario al registrarse.
    private String nombre;

    // Apellido del usuario.
    private String apellido;

    // Email del usuario.
    private String email;

    // Contraseña del usuario.
    private String password;
}