package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.TipoServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicioModel, Integer> {
    @Query("SELECT t.descripcion FROM TipoServicioModel t WHERE t.id_tiposervicio = :id_tiposervicio")
    String findById_Tiposervicio(Integer id_tiposervicio);

}
