package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.TipoServicioModel;
import com.doficios.apirest.Models.TrabajadoresModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

public interface TrabajadoresRepository extends JpaRepository<TrabajadoresModel, Integer> {
    //List<TrabajadoresModel> findTop3ByTipoServicioModel(TipoServicioModel tipoServicioModel);

    @Query("SELECT t FROM TrabajadoresModel t WHERE t.tipoServicioModel.id_tiposervicio = :tipoServicioId AND t.id_usuario " +
            "NOT IN (SELECT s.usuarioModelTrabajador.id_usuario FROM ServiciosModel s WHERE s.fecha_servicio = :fechaServicio" +
            " AND s.statusModel.status != 19 ) ORDER BY t.reputacion DESC LIMIT 3")
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

    @Transactional
    @Modifying
    @Query("UPDATE TrabajadoresModel t SET t.descripcion = :descripcion, t.experiencia = :experiencia, t.tipoServicioModel.id_tiposervicio = :tipoServicio " +
            "WHERE t.id_usuario = :idUsuario")
    void updateTrabajador(@Param("idUsuario") int idUsuario,
                          @Param("descripcion") String descripcion,
                          @Param("experiencia") String experiencia,
                          @Param("tipoServicio") int tipoServicio);

    //Insertar en candidatos_servicio
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO candidatos_servicio (id_servicio, id_trabajador, status, prioridad, intento) " +
            "VALUES (:idServicio, :idTrabajador, :status, :prioridad, :intento)", nativeQuery = true)
    void insertarCandidatoServicio(Long idServicio, Long idTrabajador, char status, int prioridad, int intento);
}
