package com.juanjo.proyetofinal.persistencia.impl;

import com.juanjo.proyetofinal.entidades.Viaje;
import com.juanjo.proyetofinal.persistencia.IViajeDAO;
import com.juanjo.proyetofinal.repositorio.RepositorioViaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Marcamos esta clase como un componente de Spring para que se gestione como un bean
@Component
public class ViajeDAOimpl implements IViajeDAO {

    // Inyección automática del repositorio de viajes
    @Autowired
    private RepositorioViaje repositorioViaje;

    // Obtiene todos los viajes almacenados en la base de datos
    @Override
    public List<Viaje> findAll() {
        return (List<Viaje>) repositorioViaje.findAll();
    }

    // Busca un viaje por su ID (devuelve Optional por si no se encuentra)
    @Override
    public Optional<Viaje> findById(int id) {
        return repositorioViaje.findById(id);
    }

    // Guarda un nuevo viaje o actualiza uno existente
    @Override
    public void save(Viaje viaje) {
        repositorioViaje.save(viaje);
    }

    // Elimina un viaje por su ID
    @Override
    public void deleteById(int id) {
        repositorioViaje.deleteById(id);
    }

    // Busca todos los viajes que tengan un destino exacto especificado
    @Override
    public List<Viaje> findViajeByDestino(String destino){
        return repositorioViaje.findByDestino(destino);
    }

    // Busca los viajes cuya fecha esté entre dos fechas dadas
    @Override
    public List<Viaje> findByFechaViajeBetween(LocalDateTime fechaViaje1, LocalDateTime fechaViaje2) {
        return repositorioViaje.findByFechaHoraBetween(fechaViaje1, fechaViaje2);
    }
}