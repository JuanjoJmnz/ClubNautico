package com.juanjo.proyetofinal.servicios;

import com.juanjo.proyetofinal.controladores.models.AuthResponse;
import com.juanjo.proyetofinal.controladores.models.AuthenticationRequest;
import com.juanjo.proyetofinal.controladores.models.RegisterRequest;

public interface AuthService {

    // Método para un nuevo registro.
    AuthResponse register(RegisterRequest registerRequest);

    // Método para autenticar.
    AuthResponse authenticate(AuthenticationRequest authenticationRequest);
}
