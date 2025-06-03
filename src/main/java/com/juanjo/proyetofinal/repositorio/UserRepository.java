package com.juanjo.proyetofinal.repositorio;

import com.juanjo.proyetofinal.entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Buscamos al usuario por el email, y devuelve Optional porque puede no existir.
    Optional<User> findByEmail(String email);
}
