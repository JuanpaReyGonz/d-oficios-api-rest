package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.TareasPorServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TareasPorServicioRepository extends JpaRepository<TareasPorServicioModel, Integer> {
    List<TareasPorServicioModel> findByIdServicio(Integer idServicio);
    List<TareasPorServicioModel> findByIdTareaAndIdServicio(Integer idTarea, Integer idServicio);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tareas_servicio (id_tarea, id_servicio, importe, iva, status, sub_servicio, unidad, total)" +
            "VALUES (:idTarea, :idServicio, :importe, :iva, :status, :subServicio, :unidad, :total)", nativeQuery = true)
    void insertTareasServicio(Integer idTarea, Long idServicio, double importe, double iva, Integer status,
                                          Integer subServicio, Integer unidad, double total);

    @Transactional
    @Modifying
    @Query("UPDATE TareasPorServicioModel SET statusTarea.status = :status WHERE idServicio = :idServicio")
    void actualizaStatusTareas(Long idServicio, Integer status);
}