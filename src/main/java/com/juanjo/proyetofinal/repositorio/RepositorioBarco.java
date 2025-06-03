package com.juanjo.proyetofinal.repositorio;

import com.juanjo.proyetofinal.entidades.Barco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
// Interfaz que extiende CrudRepository para la entidad Barco
// Proporciona métodos CRUD y personalizados para consultas específicas
public interface RepositorioBarco extends CrudRepository<Barco, Integer> {

    // Metodo para encontrar barcos cuya cuota esté entre dos valores (rango inclusivo)
    List<Barco> findBarcoByCuotaBarcoBetween(BigDecimal c1, BigDecimal c2);

    // Metodo para encontrar un barco por su nombre exacto
    Optional<Barco> findBarcoByNombreBarco(String nombre);

    // Metodo para encontrar un barco por su matrícula exacta
    Optional<Barco> findBarcoByMatriculaBarco(String matricula);

    // Metodo usando una consulta JPQL personalizada para encontrar barcos
    // cuya cuota esté entre dos valores, equivalente a findBarcoByCuotaBarcoBetween
    @Query("SELECT b FROM Barco b WHERE b.cuotaBarco BETWEEN ?1 AND ?2")
    List<Barco> findBarcoByCuotaBarcoInRange(BigDecimal c1, BigDecimal c2);

    @Query("SELECT COUNT(b) FROM Barco b")
    long countBarco();
}
