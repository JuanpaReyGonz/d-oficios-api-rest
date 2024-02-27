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
public class RegisterRequest {
    //String username;
    String correo;
    String password;
    //String firstname;
    String nombre;
    //String lastname;
    String telefono;
    //String country;
    Role tipoUsuario;

}
