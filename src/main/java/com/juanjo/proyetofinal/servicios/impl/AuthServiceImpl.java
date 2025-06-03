package com.juanjo.proyetofinal.servicios.impl;

import com.juanjo.proyetofinal.config.JwtService;
import com.juanjo.proyetofinal.controladores.models.AuthResponse;
import com.juanjo.proyetofinal.controladores.models.AuthenticationRequest;
import com.juanjo.proyetofinal.controladores.models.RegisterRequest;
import com.juanjo.proyetofinal.entidades.Role;
import com.juanjo.proyetofinal.entidades.User;
import com.juanjo.proyetofinal.repositorio.UserRepository;
import com.juanjo.proyetofinal.servicios.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    // Accede al repositorio para usar los datos del usuario.
    private final UserRepository userRepository;

    // Codificador de contraseñas.
    private final PasswordEncoder passwordEncoder;

    // Genera y valida los tokens.
    private final JwtService jwtService;

    // Gestor para autenticar en Spring
    private final AuthenticationManager authenticationManager;

    // Creo un nuevo objeto Usuario con los valores del registro.
    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        var usuario = User.builder()
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(usuario);

        // Genera un token personalizado del usuario.
        var jwtToken = jwtService.generateToken(usuario);

        // Devuelve el token.
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    // Autentica al usuario usando el email y contraseña.
    @Override
    public AuthResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        // Recupera al usuario de la BBDD.
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();

        // Genera el token para el usuario.
        var jwtToken = jwtService.generateToken(user);

        // Devuelve el token.
        return AuthResponse.builder().token(jwtToken).build();
    }
}