package com.juanjo.proyetofinal.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    // Devuelve la autoridad del usuario dependiendo de su rol.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    // Define que el email sea el nombre del usuario en el sistema.
    @Override
    public String getUsername() {
        return email;
    }

    // Devuelve la contraseña del usuario.
    @Override
    public String getPassword() {
        return password;
    }

    // Nos indica si la cuenta está caducada.
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    // Indica si la cuenta está bloqueada.
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    // Indica si las credenciales están caducadas.
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    // Indica si el usuario está habilitado.
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}