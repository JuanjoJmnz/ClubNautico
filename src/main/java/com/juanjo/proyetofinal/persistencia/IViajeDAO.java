package com.juanjo.proyetofinal.persistencia;

import com.juanjo.proyetofinal.entidades.Viaje;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Interfaz DAO para la entidad Viaje.
// Define los métodos para acceder y manipular datos relacionados con los viajes.
public interface IViajeDAO {

    // Obtiene todos los viajes registrados en la base de datos.
    List<Viaje> findAll();

    // Busca un viaje por su ID único.
    // Devuelve un Optional que puede contener el viaje o estar vacío si no se encuentra.
    Optional<Viaje> findById(int id);

    // Guarda un nuevo viaje o actualiza uno existente en la base de datos.
    void save(Viaje viaje);

    // Elimina un viaje de la base de datos según su ID.
    void deleteById(int id);

    // Busca viajes que tienen como destino el valor exacto especificado.
    List<Viaje> findViajeByDestino(String destino);

    // Busca viajes cuya fecha de salida se encuentra entre las dos fechas especificadas.
    List<Viaje> findByFechaViajeBetween(LocalDateTime fechaViaje1, LocalDateTime fechaViaje2);
}
