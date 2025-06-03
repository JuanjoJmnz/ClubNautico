package com.juanjo.proyetofinal.controladores.dto;

import com.juanjo.proyetofinal.entidades.Socio;
import com.juanjo.proyetofinal.entidades.Viaje;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BarcoDTO {

    // Identificador único del barco.
    private Long idBarco;

    // Matrícula única del barco.
    @NotBlank(message = "La matrícula del barco no puede estar vacía.")
    @Size(min = 2, max = 6, message = "La matrícula debe tener entre 2 y 6 caracteres.")
    private String matriculaBarco;

    // Nombre asignado al barco.
    @NotBlank(message = "El nombre del barco no puede estar vacío.")
    @Size(min = 2, max = 40, message = "El nombre debe tener entre 2 y 40 caracteres.")
    private String nombreBarco;


    // Número de amarre asociado al barco en el puerto.
    @Min(value = 1, message = "El número de amarre debe de ser mayor a 0.")
    private Integer numeroAmarre;


    // Cuota mensual o anual que paga el barco.
    @NotNull(message = "La cuota del barco es obligatoria.")
    @DecimalMin(value= "0.0", inclusive = false, message = "La cuota debe ser mayor a 0.")
    @Digits(integer = 10, fraction = 2, message = "La cuota debe de tener mínimo dos decimales.")
    private BigDecimal cuotaBarco;

    // Socio propietario del barco.
    // Se refiere a la relación del barco con su propietario.
    private Socio barcosDeSocio;

    // Lista de viajes que ha realizado el barco.
    // Se utiliza para representar la relación con la entidad Viaje.
    private List<Viaje> viajes = new ArrayList<>();
}
