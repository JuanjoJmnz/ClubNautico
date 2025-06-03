package com.juanjo.proyetofinal.servicios.impl;

import com.juanjo.proyetofinal.entidades.Viaje;
import com.juanjo.proyetofinal.persistencia.IViajeDAO;
import com.juanjo.proyetofinal.servicios.IViajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
// Implementación del servicio para la entidad Viaje
// Implementa la interfaz IViajeServicio que define las operaciones sobre Viaje
public class ViajeServicioImpl implements IViajeServicio {

    @Autowired
    // Inyección del DAO para acceder a la capa de persistencia de Viaje
    private IViajeDAO viajeDAO;

    @Override
    // Devuelve la lista de todos los viajes registrados
    public List<Viaje> findAll() {
        return viajeDAO.findAll();
    }

    @Override
    // Busca un viaje por su ID, devuelve Optional con el viaje o vacío si no existe
    public Optional<Viaje> findById(int id) {
        return viajeDAO.findById(id);
    }

    @Override
    // Guarda o actualiza un viaje en la base de datos
    public void save(Viaje viaje) {
        viajeDAO.save(viaje);
    }

    @Override
    // Elimina un viaje por su ID
    public void deleteById(int id) {
        viajeDAO.deleteById(id);
    }

    @Override
    // Busca viajes cuyo destino coincide con el parámetro dado
    public List<Viaje> findViajeByDestino(String destino) {
        return viajeDAO.findViajeByDestino(destino);
    }

    @Override
    // Busca viajes con fecha y hora entre las dos fechas dadas (inclusive)
    public List<Viaje> findByFechaViajeBetween(LocalDateTime fechaViaje1, LocalDateTime fechaViaje2) {
        return viajeDAO.findByFechaViajeBetween(fechaViaje1, fechaViaje2);
    }
}