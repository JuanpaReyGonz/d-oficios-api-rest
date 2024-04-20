package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.TipoServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicioModel, Integer> {
}
