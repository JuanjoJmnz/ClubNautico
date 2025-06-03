package com.juanjo.proyetofinal.controladores.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juanjo.proyetofinal.entidades.Barco;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViajeDTO {

    // Identificador único del viaje
    private Long idViaje;

    // Fecha y hora de salida del viaje
    // Debe estar presente y no puede ser una fecha pasada
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "La fecha y hora de salida es obligatoria.")
    @FutureOrPresent(message = "La hora y fecha no puede ser pasada a la actual.")
    private LocalDateTime fechaHora;

    // Destino del viaje, obligatorio con una longitud válida
    @NotBlank(message = "El destino es obligatorio.")
    @Size(min = 2, max = 120, message = "El destino debe tener entre 2 y 120 caracteres.")
    private String destino;

    // ID del socio que actúa como patrón del viaje
    private Long idPatronSocio;

    // Nombre del patrón del viaje
    @Size(min = 2, max = 50, message = "El nombre del patrón debe tener entre 2 y 50 caracteres.")
    private String nombrePatron;

    // Apellido del patrón del viaje
    @Size(min = 2, max = 50, message = "El apellido del patrón debe tener entre 2 y 50 caracteres.")
    private String apellidoPatron;

    // Teléfono del patrón, validado para tener exactamente 9 dígitos
    @Pattern(regexp = "\\d{9}", message = "El teléfono del patrón debe tener 9 caracteres.")
    private String telefonoPatron;

    // ID del barco asociado al viaje (obligatorio)
    @NotNull(message = "El viaje debe de tener un ID de barco asociado.")
    private Long idBarco;

    // Datos del barco correspondiente al viaje, encapsulados en un DTO
    private BarcoDTO datosBarco;
}
