package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.ServiciosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiciosRepository extends JpaRepository<ServiciosModel,Long> {
    //List<ServiciosModel> findByUsuarioModelTrabajadorIdAndStatus(Integer usuarioTrabajadorId, Integer status);

    @Query("SELECT COUNT(s) FROM ServiciosModel s " +
            "WHERE s.usuarioModelTrabajador.id_usuario = :usuarioTrabajadorId " +
            "AND s.statusModel.status = :status")
    int countByUsuarioModelTrabajadorIdAndStatus(@Param("usuarioTrabajadorId") Long usuarioTrabajadorId,
                                                 @Param("status") Integer status);
    List<ServiciosModel> findByUsuarioModelCorreo(String correo);
    @Query("SELECT s FROM ServiciosModel s WHERE s.id_servicio = :id_servicio")
    ServiciosModel findById_servicio(Integer id_servicio);
}
