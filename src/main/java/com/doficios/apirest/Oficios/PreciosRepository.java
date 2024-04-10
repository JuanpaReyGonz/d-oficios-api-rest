package com.doficios.apirest.Oficios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PreciosRepository extends JpaRepository<PreciosModel, PreciosId> {
    //Busca por tipo servicio y subservicio
    PreciosModel findByTipoServicioModelAndSubServiciosModel(TipoServicioModel tipoServicioModel, SubServiciosModel subServiciosModel);
}
