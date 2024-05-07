package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.DireccionesId;
import com.doficios.apirest.Models.DireccionesModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DireccionesRepository extends JpaRepository<DireccionesModel, DireccionesId> {
    @Query("SELECT d FROM DireccionesModel d WHERE d.usuarioModel.id_usuario = :usuario AND d.id_direccion = :id_direccion")
    DireccionesModel findById_UsuarioAndId_Direccion(Long usuario, Integer id_direccion);
    @Query("SELECT d FROM DireccionesModel d WHERE d.usuarioModel.id_usuario = :usuario")
    //@Query("SELECT d FROM DireccionesModel d JOIN FETCH d.entidadModel JOIN FETCH d.municipioModel JOIN FETCH d.localidadModel WHERE d.usuario = :usuario")
    List<DireccionesModel> findById_Usuario(Integer usuario);

    //Insert y Update Direcciones
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO direcciones (usuario, id_direccion, entidad, municipio, localidad, domicilio, exterior, interior, colonia, cp, favorito) " +
            "VALUES (:usuario, :idDireccion, :entidad, :municipio, :localidad, :domicilio, :exterior, :interior, :colonia, :cp, :favorito) " +
            "ON CONFLICT (usuario, id_direccion) DO UPDATE SET " +
            "entidad = :entidad, municipio = :municipio, localidad = :localidad, domicilio = :domicilio, " +
            "exterior = :exterior, interior = :interior, colonia = :colonia, cp = :cp, favorito = :favorito",
            nativeQuery = true)
    void insertOrUpdateDireccion(Long usuario, int idDireccion, int entidad, int municipio, int localidad,
                                 String domicilio, String exterior, String interior, String colonia, String cp, boolean favorito);

}
