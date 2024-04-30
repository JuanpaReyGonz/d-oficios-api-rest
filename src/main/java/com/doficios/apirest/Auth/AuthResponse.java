package com.doficios.apirest.Auth;

import com.doficios.apirest.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String nombre;
    String correo;
    Role tipoUsuario;
    String token;
}