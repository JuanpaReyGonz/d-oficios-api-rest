package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.CandidatoServicioId;
import com.doficios.apirest.Models.CandidatoServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CandidatoServicioRepository extends JpaRepository<CandidatoServicioModel, CandidatoServicioId> {
    @Query("SELECT c.prioridad FROM CandidatoServicioModel c WHERE c.id_servicio = :id_servicio AND c.id_trabajador = :id_trabajador")
    int findPrioridadByIdServicioAndIdTrabajador(Long id_servicio, Long id_trabajador);

    @Query("SELECT c FROM CandidatoServicioModel c WHERE c.id_servicio = :id_servicio AND c.prioridad = :prioridad")
    CandidatoServicioModel findCandidatoByIdServicioAndPrioridad(Long id_servicio, int prioridad);

    @Transactional
    @Modifying
    @Query("UPDATE CandidatoServicioModel SET status = :status WHERE id_servicio = :id_servicio AND id_trabajador = :id_trabajador AND prioridad = :prioridad")
    void actualizaStatusCandidatoByIdServicioAndIdTrabajador(char status, Long id_servicio, Long id_trabajador, int prioridad);


}
