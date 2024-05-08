package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.TipoServicioModel;
import com.doficios.apirest.Models.TrabajadoresModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface TrabajadoresRepository extends JpaRepository<TrabajadoresModel, Integer> {
    //List<TrabajadoresModel> findTop3ByTipoServicioModel(TipoServicioModel tipoServicioModel);

    @Query("SELECT t FROM TrabajadoresModel t WHERE t.tipoServicioModel.id_tiposervicio = :tipoServicioId AND t.id_usuario NOT IN (SELECT s.usuarioModelTrabajador.id_usuario FROM ServiciosModel s WHERE s.fecha_servicio = :fechaServicio) ORDER BY t.reputacion DESC LIMIT 3")
    List<TrabajadoresModel> findFirst3TrabajadoresByTipoServicio(@Param("tipoServicioId") Integer tipoServicioId, @Param("fechaServicio") String fechaServicio);

    /*@Query("SELECT t.id_usuario FROM TrabajadoresModel t " +
            "WHERE t.tipoServicioModel.id_tiposervicio = :tipoServicioId " +
            "AND t.id_usuario NOT IN " +
            "(SELECT s.usuarioModelTrabajador.id_usuario FROM ServiciosModel s " +
            "WHERE s.fecha_servicio = :fechaServicio) " +
            "ORDER BY t.reputacion DESC")
    List<TrabajadoresModel> findTop3TrabajadoresByTipoServicioIdAndNotInServiciosOrderByReputacionDesc(@Param("tipoServicioId") Integer tipoServicioId, @Param("fechaServicio") String fechaServicio);*/

    @Query("SELECT t FROM TrabajadoresModel t WHERE t.id_usuario = :idUsuario")
    TrabajadoresModel findTrabajadorByIdUsuario(int idUsuario);

    @Modifying
    @Query("UPDATE TrabajadoresModel t SET t.descripcion = :descripcion, t.experiencia = :experiencia, t.tipo_servicio = :tipoServicio, " +
            "t.clabe = :clabe, t.foto_trabajo = :fotoTrabajo WHERE t.id_usuario = :idUsuario")
    void updateTrabajador(@Param("idUsuario") int idUsuario,
                          @Param("descripcion") String descripcion,
                          @Param("experiencia") String experiencia,
                          @Param("tipoServicio") int tipoServicio,
                          @Param("clabe") String clabe,
                          @Param("fotoTrabajo") String fotoTrabajo,
                          @Param("codigoRegistro") String codigoRegistro);

}
