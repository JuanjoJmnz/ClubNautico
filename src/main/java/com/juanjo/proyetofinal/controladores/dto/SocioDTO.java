package com.juanjo.proyetofinal.controladores.dto;

import com.juanjo.proyetofinal.entidades.Barco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocioDTO {

    // Identificador único del socio
    private Long idSocio;

    // Nombre del socio
    @NotBlank(message = "El nombre del socio no puede estar vacío.")
    @Size(min = 2, max = 40, message = "El nombre debe tener entre 2 y 40 caracteres.")
    private String nombreSocio;

    // Apellido del socio
    @NotBlank(message = "El apellido del socio no puede estar vacío.")
    @Size(min = 2, max = 40, message = "El apellido debe tener entre 2 y 40 caracteres.")
    private String apellidoSocio;

    // Dirección de residencia del socio
    @NotBlank(message = "la dirección del socio no puede estar vacía.")
    @Size(min = 10, max = 150, message = "la dirección debe tener entre 10 y 150 caracteres.")
    private String direccionSocio;

    // Número de teléfono del socio
    @NotBlank(message = "El teléfono del socio no puede estar vacío.")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos.")
    private String telefonoSocio;

    // Lista de barcos asociados a este socio
    private List<Barco> barcos = new ArrayList<>();
}
