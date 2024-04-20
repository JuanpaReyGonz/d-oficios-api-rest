package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.ServiciosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiciosRepository extends JpaRepository<ServiciosModel,Long> {
    List<ServiciosModel> findByUsuarioModelId(Long usuarioId);
    List<ServiciosModel> findByUsuarioModelCorreo(String correo);
    @Query("SELECT s FROM ServiciosModel s WHERE s.id_servicio = :id_servicio")
    ServiciosModel findById_servicio(Integer id_servicio);
}
