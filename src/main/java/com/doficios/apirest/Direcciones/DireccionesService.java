package com.doficios.apirest.Direcciones;

import com.doficios.apirest.Models.DireccionesModel;
import com.doficios.apirest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DireccionesService {
    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    DireccionesRepository direccionesRepo;
    @Autowired
    EntidadRepository entidadRepo;
    @Autowired
    MunicipioRepository municipioRepo;
    @Autowired
    LocalidadRepository localidadRepo;
    @Transactional(readOnly = true)
    public List<DireccionesUsuarioDTO> obtenerDireccionesUsuario(String username){
        int usuario = usuarioRepo.findByCorreo(username);
        //System.out.println("Consultado direcciones del usuario "+ usuario);
        List<DireccionesModel> direccionesUsuario = direccionesRepo.findById_Usuario(usuario);
        List<DireccionesUsuarioDTO> direccionesUsuarioDTOList = new ArrayList<>();

        for (DireccionesModel direccion : direccionesUsuario){
            String entidad = entidadRepo.findByIdEntidad(direccion.getEntidad());
            String municipio = municipioRepo.findByIdEntidadAndIdMunicipio(direccion.getEntidad(), direccion.getMunicipio());
            String localidad = localidadRepo.findByIdEntidadAndIdMunicipioAndIdLocalidad(direccion.getEntidad(), direccion.getMunicipio(), direccion.getLocalidad());
            boolean favorito;
            favorito = direccion.getFavorito() == 1;
            String numInterior = direccion.getInterior();
            numInterior = (numInterior == null) ? "" : numInterior;
            DireccionesUsuarioDTO direccionesUsuarioDTO = DireccionesUsuarioDTO.builder()
                    .id_direccion(direccion.getId_direccion())
                    .entidad(entidad)
                    .municipio(municipio)
                    .localidad(localidad)
                    .domicilio(direccion.getDomicilio())
                    .exterior(direccion.getExterior())
                    .interior(numInterior)
                    .colonia(direccion.getColonia())
                    .cp(direccion.getCp())
                    .favorito(favorito)
                    .build();
            direccionesUsuarioDTOList.add(direccionesUsuarioDTO);
        }
        return direccionesUsuarioDTOList;
    }
}
