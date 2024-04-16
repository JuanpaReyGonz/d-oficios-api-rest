package com.doficios.apirest.Servicios;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareasPorServicioRepository extends JpaRepository<TareasPorServicioModel, Integer> {
    List<TareasPorServicioModel> findByIdServicio(Integer idServicio);
    List<TareasPorServicioModel> findByIdTareaAndIdServicio(Integer idTarea, Integer idServicio);
}