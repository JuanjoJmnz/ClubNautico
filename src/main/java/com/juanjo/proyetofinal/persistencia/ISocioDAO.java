package com.juanjo.proyetofinal.persistencia;

import com.juanjo.proyetofinal.entidades.Socio;

import java.util.List;
import java.util.Optional;

// Interfaz DAO para la entidad Socio.
// Define los métodos de acceso a datos relacionados con los socios del sistema.
public interface ISocioDAO {

    // Obtiene todos los socios registrados en la base de datos.
    List<Socio> findAll();

    // Busca un socio por su ID único.
    // Devuelve un Optional que puede contener un socio o estar vacío si no se encuentra.
    Optional<Socio> findById(int id);

    // Devuelve una lista de socios cuyos ID se encuentran en el rango especificado.
    List<Socio> findByIdSocioBetween(int idSocio1, int idSocio2);

    // Busca un socio por su número de teléfono exacto.
    Optional<Socio> findByTelefono(String telefono);

    // Busca socios cuyo nombre contenga el texto especificado (ignorando mayúsculas y minúsculas).
    List<Socio> findByNombreContainingIgnoreCase(String nombre);

    // Busca un socio cuyo nombre coincida exactamente (ignorando mayúsculas y minúsculas).
    Optional<Socio> findByNombreExacto(String nombre);

    // Guarda un nuevo socio o actualiza uno existente en la base de datos.
    void save(Socio socio);

    // Elimina un socio de la base de datos según su ID.
    void deleteById(int id);

    // Cuenta los socios
    Long contarSocio();
}