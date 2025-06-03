package com.juanjo.proyetofinal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

// Define que esta clase es una entidad JPA que se mapeará a la tabla "barco"
@Entity
@Table(name = "barco")
public class Barco {

    // Clave primaria con generación automática (autoincremental)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarco;

    // Campo obligatorio (no puede estar en blanco) con validación de tamaño y restricción de unicidad
    @NotBlank(message = "La matrícula del barco no puede estar vacía.")
    @Size(min = 2, max = 6, message = "La matrícula debe tener entre 2 y 6 caracteres.")
    @Column(name = "matricula", unique = true)
    private String matriculaBarco;

    // Campo obligatorio (no puede estar en blanco) con validación de tamaño
    @NotBlank(message = "El nombre del barco no puede estar vacío.")
    @Size(min = 2, max = 40, message = "El nombre debe tener entre 2 y 40 caracteres.")
    @Column(name = "nombre")
    private String nombreBarco;

    // Valor mínimo de 1 para garantizar un número de amarre válido
    @Min(value = 1, message = "El número de amarre debe de ser mayor a 0.")
    @Column(name = "numeroAmarre")
    private int numeroAmarre;

    // Cuota obligatoria con validaciones: mayor que 0 y con hasta dos decimales
    @NotNull(message = "La cuota del barco es obligatoria.")
    @DecimalMin(value= "0.0", inclusive = false, message = "La cuota debe ser mayor a 0.")
    @Digits(integer = 10, fraction = 2, message = "La cuota debe de tener mínimo dos decimales.")
    @Column(name = "cuotaBarco")
    private BigDecimal cuotaBarco;

    // Relación muchos-a-uno con la entidad Socio (el propietario del barco)
    // El barco pertenece a un solo socio, pero un socio puede tener varios barcos
    // JsonIgnore evita bucles infinitos en la serialización a JSON
    @ManyToOne
    @JoinColumn(name = "fk_id_socio", nullable = false)
    @JsonIgnore
    private Socio barcosDeSocio;

    // Relación uno-a-muchos con Viaje: un barco puede tener muchos viajes
    // CascadeType.ALL: si se elimina un barco, se eliminan sus viajes
    // fetch = LAZY: los viajes se cargan solo cuando se acceden
    // orphanRemoval = true: elimina los viajes huérfanos si se quitan del barco
    // JsonIgnore evita serialización recursiva
    @OneToMany(mappedBy = "barco", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Viaje> viajes = new ArrayList<>();
}

