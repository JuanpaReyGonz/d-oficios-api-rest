package com.doficios.apirest.Servicios;

import com.doficios.apirest.Usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiciosRepository extends JpaRepository<ServiciosModel,Long> {
}
