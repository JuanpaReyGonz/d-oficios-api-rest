package com.doficios.apirest.Servicios;

import com.doficios.apirest.Usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiciosRepository extends JpaRepository<ServiciosModel,Long> {
    List<ServiciosModel> findByUsuarioModelId(Long usuarioId);
    List<ServiciosModel> findByUsuarioModelCorreo(String correo);
}
