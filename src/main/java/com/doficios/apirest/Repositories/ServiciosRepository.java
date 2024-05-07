package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.ServiciosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ServiciosRepository extends JpaRepository<ServiciosModel,Long> {
    //List<ServiciosModel> findByUsuarioModelTrabajadorIdAndStatus(Integer usuarioTrabajadorId, Integer status);

    @Query("SELECT COUNT(s) FROM ServiciosModel s " +
            "WHERE s.usuarioModelTrabajador.id_usuario = :usuarioTrabajadorId " +
            "AND s.statusModel.status = :status")
    int countByUsuarioModelTrabajadorIdAndStatus(@Param("usuarioTrabajadorId") Long usuarioTrabajadorId,
                                                 @Param("status") Integer status);

    @Query("SELECT COUNT(s) FROM ServiciosModel s " +
            "WHERE s.usuarioModel.id_usuario = :usuarioClienteId " +
            "AND s.statusModel.status = :status")
    int countByUsuarioModelIdAndStatus(@Param("usuarioClienteId") Long usuarioClienteId,
                                                 @Param("status") Integer status);

    List<ServiciosModel> findByUsuarioModelCorreo(String correo);
    List<ServiciosModel> findByUsuarioModelTrabajadorCorreo(String correo);
    @Query("SELECT s FROM ServiciosModel s WHERE s.id_servicio = :id_servicio")
    ServiciosModel findById_servicio(Integer id_servicio);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO servicios (cliente, trabajador, status, fecha_solicitud, fecha_servicio, hora_servicio, importe, comision, tipo_servicio, entidad, municipio, localidad, domicilio, exterior, interior, colonia, cp) " +
            "VALUES (:cliente, :trabajador, :status, :fechaSolicitud, :fechaServicio, :horaServicio, :importe, :comision, :tipoServicio, :entidad, :municipio, :localidad, :domicilio, :exterior, :interior, :colonia, :cp)",
            nativeQuery = true)
    void insertarServicio(Long cliente, Long trabajador, Integer status, LocalDateTime fechaSolicitud, LocalDate fechaServicio,
                          LocalTime horaServicio, double importe, double comision, Integer tipoServicio, Integer entidad, Integer municipio,
                          Integer localidad, String domicilio, String exterior, String interior, String colonia, String cp);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long getLastInsertedId();

    @Transactional
    @Modifying
    @Query("UPDATE ServiciosModel u SET u.statusModel.status = :status WHERE u.id_servicio = :idServicio")
    void actualizaStatusServicio(Long idServicio, Integer status);

    @Transactional
    @Modifying
    @Query("UPDATE ServiciosModel u SET calificacion = :calificacion, comentario = :comentario WHERE u.id_servicio = :idServicio")
    void actualizaCalificacionServicioByIdServicio(Integer calificacion, String comentario, Long idServicio);
}
