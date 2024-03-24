package com.doficios.apirest.Oficios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicioModel, Integer> {
}
