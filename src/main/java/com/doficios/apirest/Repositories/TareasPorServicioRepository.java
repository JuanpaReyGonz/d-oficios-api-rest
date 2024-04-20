package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.TareasPorServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareasPorServicioRepository extends JpaRepository<TareasPorServicioModel, Integer> {
    List<TareasPorServicioModel> findByIdServicio(Integer idServicio);
    List<TareasPorServicioModel> findByIdTareaAndIdServicio(Integer idTarea, Integer idServicio);
}