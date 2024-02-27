package com.doficios.apirest.User;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Table(name="usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"correo","telefono"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    //Integer id;
    Long id_usuario;
    @Basic
    @Column(nullable = false, unique = true)
    //String username;
    String correo;
    @Column(nullable = false, unique = true)
    //String lastname;
    String telefono;
    //String firstname;
    String nombre;
    //String country;
    String password;
    @Column(name="tipo_usuario")
    @Enumerated(EnumType.STRING)
    Role tipoUsuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((tipoUsuario.name())));
    }

    @Override
    public String getUsername() {
        return correo; //No se porqué es de ahuevo este metodo.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
