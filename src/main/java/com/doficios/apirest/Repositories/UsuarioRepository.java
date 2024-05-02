package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.UsuarioModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    public abstract ArrayList<UsuarioModel> findBytipoUsuario(char tipoUsuario);
    @Query("SELECT u.id_usuario FROM UsuarioModel u WHERE u.correo = :correo")
    int findByCorreo(String correo);
}
