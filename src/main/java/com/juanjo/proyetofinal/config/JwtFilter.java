package com.juanjo.proyetofinal.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    // Inyecto el servicio para cargar los detalles del usuario desde la BBDD
    private final UserDetailsService userDetailsService;

    // Inyecto el servicio para trabajar con operaciones JWT
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        // Me devuelve el header "Authorization"
        final String authorizationHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Verificamos si el Header es nulo o si no comienza con "Bearer". Si es así, continúa con el filtro.
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Si todo esta correcto, empieza a leer desde el septimo caracter.
        jwt = authorizationHeader.substring(7);

        // Estraemos el email del usuario
        userEmail = jwtService.getUserName(jwt);

        // Compruebo que el usuario no sea nulo ni que ya esté autenticado.
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Validamos el token JWT
            if(jwtService.validateToken(jwt,userDetails)){

                // Creamos un token de Spring Security
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Se asocian los detalles de la petición al token.
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Se establece la autenticación
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
