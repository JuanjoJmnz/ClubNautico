package com.juanjo.proyetofinal.persistencia.impl;

import com.juanjo.proyetofinal.entidades.Socio;
import com.juanjo.proyetofinal.persistencia.ISocioDAO;
import com.juanjo.proyetofinal.repositorio.RepositorioSocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

// Marcamos la clase como un componente de Spring para que se gestione automáticamente
@Component
public class SocioDAOimpl implements ISocioDAO {

    // Inyectamos el repositorio de socios que se encarga del acceso a datos
    @Autowired
    private RepositorioSocio repositorioSocio;

    // Devuelve una lista con todos los socios de la base de datos
    @Override
    public List<Socio> findAll() {
        return (List<Socio>) repositorioSocio.findAll();
    }

    // Busca un socio por su ID (devuelve un Optional por si no existe)
    @Override
    public Optional<Socio> findById(int id) {
        return repositorioSocio.findById(id);
    }

    // Busca socios cuyos ID estén entre dos valores (inclusive)
    @Override
    public List<Socio> findByIdSocioBetween(int idSocio1, int idSocio2) {
        return repositorioSocio.findByIdRange(idSocio1, idSocio2);
    }

    // Busca un socio por su número de teléfono exacto
    @Override
    public Optional<Socio> findByTelefono(String telefono) {
        return repositorioSocio.findSocioByTelefonoSocio(telefono);
    }

    // Busca socios cuyo nombre contenga una cadena dada, sin importar mayúsculas o minúsculas
    @Override
    public List<Socio> findByNombreContainingIgnoreCase(String nombre) {
        return repositorioSocio.findSocioByNombreSocioContainingIgnoreCase(nombre);
    }

    // Busca un socio por su nombre exacto, ignorando mayúsculas y minúsculas
    @Override
    public Optional<Socio> findByNombreExacto(String nombre) {
        return repositorioSocio.findSocioByNombreSocioIgnoreCase(nombre);
    }

    // Guarda o actualiza un socio en la base de datos
    @Override
    public void save(Socio socio) {
        repositorioSocio.save(socio);
    }

    // Elimina un socio por su ID
    @Override
    public void deleteById(int id) {
        repositorioSocio.deleteById(id);
    }

    @Override
    public Long contarSocio() {
        return repositorioSocio.countSocio();
    }
}