package com.juanjo.proyetofinal.persistencia.impl;

import com.juanjo.proyetofinal.entidades.Barco;
import com.juanjo.proyetofinal.persistencia.IBarcoDAO;
import com.juanjo.proyetofinal.repositorio.RepositorioBarco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Marca esta clase como un componente de Spring para que sea detectado automáticamente
@Component
public class BarcoDAOimpl implements IBarcoDAO {

    // Inyección automática del repositorio de Barco que interactúa con la base de datos
    @Autowired
    private RepositorioBarco repositorioBarco;

    // Recupera todos los barcos desde la base de datos
    @Override
    public List<Barco> findAll() {
        return (List<Barco>) repositorioBarco.findAll();
    }

    // Busca un barco por su ID (entero)
    @Override
    public Optional<Barco> findById(int id) {
        return repositorioBarco.findById(id);
    }

    // Busca barcos cuya cuota esté entre un rango dado (mínimo y máximo)
    @Override
    public List<Barco> findByCuotaBarcoInRange(BigDecimal min, BigDecimal max) {
        return repositorioBarco.findBarcoByCuotaBarcoBetween(min, max);
    }

    // Busca un barco por su nombre exacto
    @Override
    public Optional<Barco> findByNombreBarco(String nombreBarco) {
        return repositorioBarco.findBarcoByNombreBarco(nombreBarco);
    }

    // Busca un barco por su matrícula exacta
    @Override
    public Optional<Barco> findByMatriculaBarco(String matriculaBarco) {
        return repositorioBarco.findBarcoByMatriculaBarco(matriculaBarco);
    }

    @Override
    public Long contarBarco() {
        return repositorioBarco.countBarco();
    }

    // Guarda o actualiza un objeto Barco en la base de datos
    @Override
    public void save(Barco barco) {
        repositorioBarco.save(barco);
    }

    // Elimina un barco por su ID (entero)
    @Override
    public void deleteById(int id) {
        repositorioBarco.deleteById(id);
    }
}