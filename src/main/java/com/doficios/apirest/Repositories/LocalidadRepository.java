package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.LocalidadesId;
import com.doficios.apirest.Models.LocalidadesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepository extends JpaRepository<LocalidadesModel, LocalidadesId> {
    @Query("SELECT l.nombre FROM LocalidadesModel l WHERE l.id_entidad = :id_entidad AND l.id_municipio = :id_municipio AND l.id_localidad = :id_localidad")
    String findByIdEntidadAndIdMunicipioAndIdLocalidad(Integer id_entidad, Integer id_municipio, Integer id_localidad);
}
