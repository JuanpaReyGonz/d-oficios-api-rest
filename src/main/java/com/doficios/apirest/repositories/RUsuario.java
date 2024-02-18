package com.doficios.apirest.repositories;

import com.doficios.apirest.models.MUsuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RUsuario extends CrudRepository<MUsuario, Long> {

}
