package com.doficios.apirest.Oficios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficiosRepository extends CrudRepository<OficiosModel, Long> {
}
