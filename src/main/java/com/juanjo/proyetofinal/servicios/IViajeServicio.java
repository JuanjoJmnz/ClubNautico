package com.juanjo.proyetofinal.servicios;

import com.juanjo.proyetofinal.entidades.Viaje;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IViajeServicio {
    // Devuelve todos los viajes registrados
    List<Viaje> findAll();

    // Busca un viaje por su ID, devuelve un Optional (puede estar vacío)
    Optional<Viaje> findById(int id);

    // Guarda o actualiza un viaje
    void save(Viaje viaje);

    // Elimina un viaje por su ID
    void deleteById(int id);

    // Busca viajes con destino exacto
    List<Viaje> findViajeByDestino(String destino);

    // Busca viajes cuyo fechaHora está entre fechaViaje1 y fechaViaje2
    List<Viaje> findByFechaViajeBetween(LocalDateTime fechaViaje1, LocalDateTime fechaViaje2);
}