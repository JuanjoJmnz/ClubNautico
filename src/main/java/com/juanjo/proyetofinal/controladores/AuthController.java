package com.juanjo.proyetofinal.controladores;


import com.juanjo.proyetofinal.controladores.models.AuthResponse;
import com.juanjo.proyetofinal.controladores.models.AuthenticationRequest;
import com.juanjo.proyetofinal.controladores.models.RegisterRequest;
import com.juanjo.proyetofinal.servicios.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    // Inyecto el servicio de la lógica de autenticación y registro.
    // Podría declararlo como "final", ya que estoy usando @RequiredArgsConstructor, o usar @Autowired.
    // @Autowired
    private final AuthService authService;

    // Registra un nuevo usuario. Devuelve una respuesta HTTP con el token de seguridad.
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    // Autentica un usuario existente. También devuelve el token mediante respuesta HTTP.
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}