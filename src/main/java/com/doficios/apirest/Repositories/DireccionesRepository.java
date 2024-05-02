package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.DireccionesId;
import com.doficios.apirest.Models.DireccionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DireccionesRepository extends JpaRepository<DireccionesModel, DireccionesId> {
    @Query("SELECT d FROM DireccionesModel d WHERE d.usuarioModel.id_usuario = :usuario AND d.id_direccion = :id_direccion")
    DireccionesModel findById_UsuarioAndId_Direccion(Integer usuario, Integer id_direccion);
    @Query("SELECT d FROM DireccionesModel d WHERE d.usuarioModel.id_usuario = :usuario")
    //@Query("SELECT d FROM DireccionesModel d JOIN FETCH d.entidadModel JOIN FETCH d.municipioModel JOIN FETCH d.localidadModel WHERE d.usuario = :usuario")
    List<DireccionesModel> findById_Usuario(Integer usuario);

}
