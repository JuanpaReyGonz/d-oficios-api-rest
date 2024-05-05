package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.HistorialStatusModel;
import com.doficios.apirest.Models.StatusDeServicioModel;
import com.doficios.apirest.Models.HistorialStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface HistorialStatusRepository extends JpaRepository<HistorialStatusModel, HistorialStatusId> {
    @Query("SELECT h FROM HistorialStatusModel h WHERE h.idServicio = :idServicio AND h.idUsuario = :idUsuario")
    List<HistorialStatusModel> findByIdServicioAndIdUsuario(Integer idServicio, Long idUsuario);

    @Query("SELECT h FROM HistorialStatusModel h JOIN FETCH h.statusModel s WHERE h.idServicio = :idServicio AND h.idUsuario = :idUsuario AND s.status = :status")
    List<HistorialStatusModel> findByIdServicioAndIdUsuarioAndStatus(Integer idServicio, Long idUsuario, StatusDeServicioModel status);

    @Query("SELECT h FROM HistorialStatusModel h WHERE h.idServicio = :idServicio AND h.idUsuario = :idUsuario ORDER BY h.fecha DESC LIMIT 1")
    HistorialStatusModel findLastStatusByIdServicioAndIdUsuario(Long idServicio, Long idUsuario);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO historial_status (id_servicio, id_usuario, status, fecha)" +
            "VALUES (:idServicio, :idUsuario, :status, :fecha)", nativeQuery = true)
    void insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(Long idServicio, Long idUsuario, Integer status, LocalDateTime fecha);
}
