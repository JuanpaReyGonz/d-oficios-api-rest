package com.doficios.apirest.Servicios;

import com.doficios.apirest.Usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ServiciosRepository extends JpaRepository<ServiciosModel,Long> {
    List<ServiciosModel> findByUsuarioModelId(Long usuarioId);
    List<ServiciosModel> findByUsuarioModelCorreo(String correo);
    @Query("SELECT s FROM ServiciosModel s WHERE s.id_servicio = :id_servicio")
    ServiciosModel findById_servicio(Integer id_servicio);
}
