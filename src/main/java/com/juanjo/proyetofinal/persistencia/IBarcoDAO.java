package com.juanjo.proyetofinal.persistencia;

import com.juanjo.proyetofinal.entidades.Barco;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Interfaz DAO (Data Access Object) para la entidad Barco.
// Define los métodos para interactuar con la base de datos relacionados con los barcos.
public interface IBarcoDAO {

    // Obtiene una lista con todos los barcos existentes en la base de datos.
    List<Barco> findAll();

    // Busca un barco por su ID único.
    // Retorna un Optional que puede contener el barco encontrado o estar vacío si no existe.
    Optional<Barco> findById(int id);

    // Busca todos los barcos cuya cuota se encuentre entre dos valores específicos.
    List<Barco> findByCuotaBarcoInRange(BigDecimal min, BigDecimal max);

    // Busca un barco cuyo nombre coincida exactamente con el proporcionado.
    Optional<Barco> findByNombreBarco(String nombreBarco);

    // Busca un barco por su matrícula exacta.
    Optional<Barco> findByMatriculaBarco(String matriculaBarco);

    Long contarBarco();

    // Guarda un nuevo barco o actualiza uno existente en la base de datos.
    void save(Barco barco);

    // Elimina un barco de la base de datos usando su ID.
    void deleteById(int id);
}