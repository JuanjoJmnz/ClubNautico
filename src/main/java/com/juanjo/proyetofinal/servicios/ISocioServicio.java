package com.juanjo.proyetofinal.servicios;

import com.juanjo.proyetofinal.entidades.Socio;

import java.util.List;
import java.util.Optional;

public interface ISocioServicio {
    // Devuelve todos los socios registrados
    List<Socio> findAll();

    // Busca un socio por su ID, devuelve un Optional (puede estar vacío)
    Optional<Socio> findById(int id);

    // Guarda o actualiza un socio
    void save(Socio socio);

    // Elimina un socio por su ID
    void deleteById(int id);

    // Busca socios cuyo ID está en el rango [min, max]
    List<Socio> findByIdRange(int min, int max);

    // Busca un socio por su teléfono exacto
    Optional<Socio> findByTelefono(String telefono);

    // Busca socios cuyo nombre contiene la cadena indicada, sin importar mayúsculas/minúsculas
    List<Socio> findByNombreContainingIgnoreCase(String nombre);

    // Busca un socio por nombre exacto, sin importar mayúsculas/minúsculas
    Optional<Socio> findByNombreExacto(String nombre);

    // Cuenta socios
    Long contarSocio();
}