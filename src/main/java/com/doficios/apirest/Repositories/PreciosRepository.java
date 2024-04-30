package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.PreciosId;
import com.doficios.apirest.Models.PreciosModel;
import com.doficios.apirest.Models.SubServiciosModel;
import com.doficios.apirest.Models.TipoServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PreciosRepository extends JpaRepository<PreciosModel, PreciosId> {
    //Busca por tipo servicio y subservicio
    PreciosModel findByTipoServicioModelAndSubServiciosModel(TipoServicioModel tipoServicioModel, SubServiciosModel subServiciosModel);
    @Query("SELECT p FROM PreciosModel p " +
            "WHERE p.tipoServicioModel.id_tiposervicio = :tipoServicio " +
            "AND p.subServiciosModel.idSubservicio = :tipoSubServicio")
    PreciosModel findByTipoServicioModelIdAndSubServiciosModelId(Integer tipoServicio, Integer tipoSubServicio);
}
