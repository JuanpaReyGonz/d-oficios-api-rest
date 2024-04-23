package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.CalificacionesId;
import com.doficios.apirest.Models.CalificacionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalificacionesRepository extends JpaRepository<CalificacionesModel, CalificacionesId> {
    @Query("SELECT c FROM CalificacionesModel c WHERE c.id_usuario = :idUsuario")
    List<CalificacionesModel> findById_usuario(Long idUsuario);

    @Query("SELECT COUNT(c) FROM CalificacionesModel c WHERE c.id_usuario = :idUsuario")
    int countById_usuario(Long idUsuario);
}
