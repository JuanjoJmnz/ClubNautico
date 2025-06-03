package com.juanjo.proyetofinal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

// Marca la clase como entidad JPA y especifica la tabla correspondiente
@Entity
@Table(name = "socio")
public class Socio {

    // Clave primaria con autoincremento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSocio;

    // Campo obligatorio, no puede estar en blanco, entre 2 y 40 caracteres
    @NotBlank(message = "El nombre del socio no puede estar vacío.")
    @Size(min = 2, max = 40, message = "El nombre debe tener entre 2 y 40 caracteres.")
    @Column(name = "nombre")
    private String nombreSocio;

    // Campo obligatorio, no puede estar en blanco, entre 2 y 40 caracteres
    @NotBlank(message = "El apellido del socio no puede estar vacío.")
    @Size(min = 2, max = 40, message = "El apellido debe tener entre 2 y 40 caracteres.")
    @Column(name = "apellido")
    private String apellidoSocio;

    // Campo obligatorio, no puede estar en blanco, entre 10 y 150 caracteres
    @NotBlank(message = "la dirección del socio no puede estar vacía.")
    @Size(min = 10, max = 150, message = "la dirección debe tener entre 10 y 150 caracteres.")
    @Column(name = "direccion")
    private String direccionSocio;

    // Campo obligatorio, exactamente 9 dígitos numéricos (expresión regular)
    @NotBlank(message = "El teléfono del socio no puede estar vacío.")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos.")
    @Column(name = "telefono")
    private String telefonoSocio;

    // Relación uno-a-muchos con Barco: un socio puede tener varios barcos
    // mappedBy indica el campo en Barco que posee la clave foránea
    // CascadeType.ALL propaga operaciones como persistir o eliminar
    // LAZY para que se cargue solo cuando se accede
    // orphanRemoval elimina barcos que se quitan de la lista
    // JsonIgnore para evitar ciclos infinitos al serializar en JSON
    @OneToMany(mappedBy = "barcosDeSocio", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Barco> barcos = new ArrayList<>();
}

