package com.doficios.apirest.Oficios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubServiciosRepository extends JpaRepository<SubServiciosModel,Integer> {
    @Query("SELECT s.descripcion_subservicios FROM SubServiciosModel s WHERE s.tiposServicio.id_tiposervicio = :idTipoServicio AND s.idSubservicio = :idSubservicio")
    String findDescripcionSubservicioByTipoAndId(@Param("idTipoServicio") Integer idTipoServicio, @Param("idSubservicio") Integer idSubservicio);
}
