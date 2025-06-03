package com.juanjo.proyetofinal.servicios.impl;

import com.juanjo.proyetofinal.entidades.Socio;
import com.juanjo.proyetofinal.persistencia.ISocioDAO;
import com.juanjo.proyetofinal.servicios.ISocioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Implementación del servicio para la entidad Socio
// Implementa la interfaz ISocioServicio que define las operaciones sobre Socio
public class SocioServicioImpl implements ISocioServicio {

    @Autowired
    // Inyección del DAO para acceder a la capa de persistencia de Socio
    private ISocioDAO socioDAO;

    @Override
    // Devuelve todos los socios registrados
    public List<Socio> findAll() {
        return socioDAO.findAll();
    }

    @Override
    // Busca un socio por su ID y devuelve un Optional con el socio o vacío
    public Optional<Socio> findById(int id) {
        return socioDAO.findById(id);
    }

    @Override
    // Guarda o actualiza un socio en la base de datos
    public void save(Socio socio) {
        socioDAO.save(socio);
    }

    @Override
    // Elimina un socio por su ID
    public void deleteById(int id) {
        socioDAO.deleteById(id);
    }

    @Override
    // Busca socios cuyo ID está dentro del rango [min, max]
    public List<Socio> findByIdRange(int min, int max) {
        return socioDAO.findByIdSocioBetween(min, max);
    }

    @Override
    // Busca un socio por su teléfono, devuelve Optional
    public Optional<Socio> findByTelefono(String telefono) {
        return socioDAO.findByTelefono(telefono);
    }

    @Override
    // Busca socios cuyo nombre contiene la cadena dada, sin distinguir mayúsculas/minúsculas
    public List<Socio> findByNombreContainingIgnoreCase(String nombre) {
        return socioDAO.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    // Busca un socio por nombre exacto, sin distinguir mayúsculas/minúsculas, devuelve Optional
    public Optional<Socio> findByNombreExacto(String nombre) {
        return socioDAO.findByNombreExacto(nombre);
    }

    @Override
    public Long contarSocio() {
        return socioDAO.contarSocio();
    }
}