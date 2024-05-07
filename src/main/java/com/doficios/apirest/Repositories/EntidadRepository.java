package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.EntidadesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadRepository extends JpaRepository<EntidadesModel,Integer> {
    @Query("SELECT e.nombre FROM EntidadesModel e WHERE e.id_entidad = :id_entidad")
    String findByIdEntidad(Integer id_entidad);
}
