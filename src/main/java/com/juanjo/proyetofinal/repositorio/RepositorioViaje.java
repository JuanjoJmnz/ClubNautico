package com.juanjo.proyetofinal.repositorio;

import com.juanjo.proyetofinal.entidades.Viaje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
// Interfaz que extiende CrudRepository para la entidad Viaje
// Proporciona métodos CRUD y consultas personalizadas para la entidad Viaje
public interface RepositorioViaje extends CrudRepository<Viaje, Integer> {

    // Metodo que devuelve una lista de viajes cuyo destino coincide exactamente con el parámetro
    List<Viaje> findByDestino(String destino);

    // Metodo que devuelve una lista de viajes cuya fecha y hora están entre las fechas proporcionadas
    List<Viaje> findByFechaHoraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}