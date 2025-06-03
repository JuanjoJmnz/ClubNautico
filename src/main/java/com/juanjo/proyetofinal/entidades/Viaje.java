package com.juanjo.proyetofinal.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

// Marca esta clase como entidad JPA y define la tabla correspondiente
@Entity
@Table(name = "viaje")
public class Viaje {

    // Clave primaria autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idViaje;

    // Fecha y hora del viaje con formato específico para JSON
    // No puede ser nula ni en el pasado
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Define el formato de salida JSON
    @NotNull(message = "La fecha y hora de salida es obligatoria.")
    @FutureOrPresent(message = "La hora y fecha no puede ser pasada a la actual.")
    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;

    // Destino del viaje, obligatorio, entre 2 y 120 caracteres
    @NotBlank(message = "El destino es obligatorio.")
    @Size(min = 2, max = 120, message = "El destino debe tener entre 2 y 120 caracteres.")
    @Column(name = "destino")
    private String destino;

    // Relación ManyToOne con Socio: el socio que actúa como patrón del viaje
    // Puede ser nulo si no se registra un socio patrón
    @ManyToOne
    @JoinColumn(name = "id_patron_socio", nullable = true)
    private Socio socioPatron;

    // Datos duplicados del patrón (nombre, apellido, teléfono) almacenados directamente
    // Útiles si no se registra un socio como patrón, o para mantener historial estático

    @Size(min = 2, max = 50, message = "El nombre del patrón debe tener entre 2 y 50 caracteres.")
    @Column(name = "nombrePatron")
    private String nombrePatron;

    @Size(min = 2, max = 50, message = "El apellido del patrón debe tener entre 2 y 50 caracteres.")
    @Column(name = "apellidoPatron")
    private String apellidoPatron;

    // Teléfono del patrón, exactamente 9 dígitos
    @Pattern(regexp = "\\d{9}", message = "El teléfono del patrón debe tener 9 caracteres.")
    @Column(name = "telefonoPatron")
    private String telefonoPatron;

    // Relación obligatoria con un barco (ManyToOne)
    // JsonIgnore evita la serialización para prevenir ciclos infinitos
    @NotNull(message = "El viaje debe de tener un barco asociado.")
    @ManyToOne
    @JoinColumn(name = "fk_id_barco")
    @JsonIgnore
    private Barco barco;
}
