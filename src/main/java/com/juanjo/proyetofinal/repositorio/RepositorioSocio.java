package com.juanjo.proyetofinal.repositorio;

import com.juanjo.proyetofinal.entidades.Barco;
import com.juanjo.proyetofinal.entidades.Socio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
// Interfaz que extiende CrudRepository para la entidad Socio
// Proporciona métodos CRUD y consultas personalizadas para la entidad Socio
public interface RepositorioSocio extends CrudRepository<Socio, Integer> {

    // Consulta personalizada con JPQL para obtener socios cuyo id esté entre un rango (min y max)
    @Query("SELECT s FROM Socio s WHERE s.idSocio BETWEEN :min AND :max")
    List<Socio> findByIdRange(@Param("min") int min, @Param("max") int max);

    // Metodo que devuelve una lista de socios cuyo nombre contiene la cadena indicada, ignorando mayúsculas/minúsculas
    List<Socio> findSocioByNombreSocioContainingIgnoreCase(String nombreSocio);

    // Metodo para buscar un socio por su número de teléfono exacto
    Optional<Socio> findSocioByTelefonoSocio(String telefono);

    // Metodo para buscar un socio por nombre exacto, ignorando mayúsculas/minúsculas
    Optional<Socio> findSocioByNombreSocioIgnoreCase(String nombreSocio);

    @Query("SELECT COUNT(s) FROM Socio s")
    long countSocio();
}