package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    public abstract ArrayList<UsuarioModel> findBytipoUsuario(char tipoUsuario);
}
