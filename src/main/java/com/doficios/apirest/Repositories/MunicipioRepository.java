package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.MunicipiosId;
import com.doficios.apirest.Models.MunicipiosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipiosModel, MunicipiosId> {
    @Query("SELECT m.nombre FROM MunicipiosModel m WHERE m.id_entidad = :id_entidad AND m.id_municipio = :id_municipio")
    String findByIdEntidadAndIdMunicipio(Integer id_entidad, Integer id_municipio);

    @Query("SELECT m FROM MunicipiosModel m WHERE m.id_entidad = :id_entidad")
    List<MunicipiosModel> findAllByIdEntidad(int id_entidad);
}
