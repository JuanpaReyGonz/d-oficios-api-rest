package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.PreciosId;
import com.doficios.apirest.Models.PreciosModel;
import com.doficios.apirest.Models.SubServiciosModel;
import com.doficios.apirest.Models.TipoServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreciosRepository extends JpaRepository<PreciosModel, PreciosId> {
    //Busca por tipo servicio y subservicio
    PreciosModel findByTipoServicioModelAndSubServiciosModel(TipoServicioModel tipoServicioModel, SubServiciosModel subServiciosModel);
}
