package com.juanjo.proyetofinal.servicios;

import com.juanjo.proyetofinal.entidades.Barco;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBarcoServicio {
    // Devuelve todos los barcos disponibles
    List<Barco> findAll();

    // Busca un barco por su ID, devuelve un Optional (puede estar vacío)
    Optional<Barco> findById(int id);

    // Busca barcos cuyo valor de cuota esté dentro del rango [min, max]
    List<Barco> findByCuotaBarcoInRange(BigDecimal min, BigDecimal max);

    // Busca un barco por nombre exacto
    Optional<Barco> findByNombreBarco(String nombreBarco);

    // Busca un barco por matrícula exacta
    Optional<Barco> findByMatriculaBarco(String matriculaBarco);

    // Cuenta barcos
    Long contarBarco();

    // Guarda o actualiza un barco
    void save(Barco barco);

    // Elimina un barco por su ID
    void deleteById(int id);
}