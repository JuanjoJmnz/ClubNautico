package com.juanjo.proyetofinal.servicios.impl;

import com.juanjo.proyetofinal.entidades.Barco;
import com.juanjo.proyetofinal.persistencia.IBarcoDAO;
import com.juanjo.proyetofinal.servicios.IBarcoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
// Clase de implementación del servicio para la entidad Barco
// Implementa la interfaz IBarcoServicio que define las operaciones disponibles
public class BarcoServicioImpl implements IBarcoServicio {

    @Autowired
    // Inyección de dependencia del DAO para acceder a la persistencia de datos
    private IBarcoDAO barcoDAO;

    @Override
    // Devuelve la lista completa de barcos
    public List<Barco> findAll() {
        return barcoDAO.findAll();
    }

    @Override
    // Busca un barco por su ID y devuelve un Optional que puede contener el barco o estar vacío
    public Optional<Barco> findById(int id) {
        return barcoDAO.findById(id);
    }

    @Override
    // Busca barcos cuya cuota está en el rango especificado entre min y max
    public List<Barco> findByCuotaBarcoInRange(BigDecimal min, BigDecimal max) {
        return barcoDAO.findByCuotaBarcoInRange(min, max);
    }

    @Override
    // Busca un barco por nombre exacto, devuelve Optional
    public Optional<Barco> findByNombreBarco(String nombreBarco) {
        return barcoDAO.findByNombreBarco(nombreBarco);
    }

    @Override
    // Busca un barco por matrícula exacta, devuelve Optional
    public Optional<Barco> findByMatriculaBarco(String matriculaBarco) {
        return barcoDAO.findByMatriculaBarco(matriculaBarco);
    }

    @Override
    public Long contarBarco() {
        return barcoDAO.contarBarco();
    }

    @Override
    // Guarda o actualiza un barco en la base de datos
    public void save(Barco barco) {
        barcoDAO.save(barco);
    }

    @Override
    // Elimina un barco por su ID
    public void deleteById(int id) {
        barcoDAO.deleteById(id);
    }
}