package com.juanjo.proyetofinal.controladores.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    // Almacena el token JWT que se devuelve al usuario una vez se autentique
    private String token;
}
