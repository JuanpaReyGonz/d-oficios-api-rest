package com.doficios.apirest.Direcciones;

import com.doficios.apirest.Models.DireccionesModel;
import com.doficios.apirest.Models.EntidadesModel;
import com.doficios.apirest.Models.LocalidadesModel;
import com.doficios.apirest.Models.MunicipiosModel;
import com.doficios.apirest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

    /*@Transactional
    public DireccionCreateUpdateResponse createUpdateDireccion(@RequestBody DireccionCreateUpdateRequest request, String username){
        Long usuario = (long) usuarioRepo.findByCorreo(username);
        direccionesRepo.insertOrUpdateDireccion(usuario, request.get);
        return DireccionCreateUpdateResponse.builder()
                .id_direccion(1)
                .build();
    }*/

    @Transactional(readOnly = true)
    public List<EntidadesModel> obtenerEntidades(){
        return (List<EntidadesModel>) entidadRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<MunicipiosByEntidadResponse> obtenerMunicipios(int idEntidad){
        List<MunicipiosModel> municipiosModel = municipioRepo.findAllByIdEntidad(idEntidad);
        List<MunicipiosByEntidadResponse> municipiosResponseList = new ArrayList<>();
        for (MunicipiosModel municipio : municipiosModel){
            MunicipiosByEntidadResponse municipiosByEntidadResponse = new MunicipiosByEntidadResponse();
            municipiosByEntidadResponse.setId_municipio(municipio.getId_municipio());
            municipiosByEntidadResponse.setNombre(municipio.getNombre());
            municipiosResponseList.add(municipiosByEntidadResponse);
        }
        return municipiosResponseList;
    }


    public List<LocalidadesByEntidadAndMunicipioResponse> obtenerLocalidades(int entidad, int municipio) {
        List<LocalidadesModel> localidadesModel = localidadRepo.findAllByIdEntidadAndIdMunicipio(entidad,municipio);
        List<LocalidadesByEntidadAndMunicipioResponse> localidadesList = new ArrayList<>();
        for (LocalidadesModel localidad : localidadesModel){
            LocalidadesByEntidadAndMunicipioResponse localidadesResponse = new LocalidadesByEntidadAndMunicipioResponse();
            localidadesResponse.setId_localidad(localidad.getId_localidad());
            localidadesResponse.setNombre(localidad.getNombre());
            localidadesList.add(localidadesResponse);
        }
        return localidadesList;
    }
}
