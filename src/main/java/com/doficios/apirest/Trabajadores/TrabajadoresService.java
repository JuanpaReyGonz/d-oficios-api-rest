package com.doficios.apirest.Trabajadores;

import com.doficios.apirest.Models.CalificacionesModel;
import com.doficios.apirest.Models.TrabajadoresModel;
import com.doficios.apirest.Repositories.CalificacionesRepository;
import com.doficios.apirest.Repositories.ServiciosRepository;
import com.doficios.apirest.Repositories.TrabajadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrabajadoresService {
    @Autowired
    TrabajadoresRepository trabajadoresRepo;
    @Autowired
    ServiciosRepository serviciosRepo;
    @Autowired
    CalificacionesRepository calificacionesRepo;

    public List<TrabajadoresDisponiblesDTO> obtenerPerfilesTrabajador(){

        List<TrabajadoresDisponiblesDTO> trabajadoresDisponiblesLista = new ArrayList<>();

        List<TrabajadoresModel> trabajadores = trabajadoresRepo.findFirst3TrabajadoresByTipoServicio(1,"2024-04-25");

        for (TrabajadoresModel trabajador : trabajadores){
            TrabajadoresDisponiblesDTO trabajadoresDisponiblesDTO = new TrabajadoresDisponiblesDTO();
            //trabajadoresDisponiblesDTO.setId_trabajador(trabajador.getId_usuario());
            trabajadoresDisponiblesDTO.setId_trabajador(trabajador.getUsuarioModel().getId());
            trabajadoresDisponiblesDTO.setNombre(trabajador.getUsuarioModel().getNombre());
            trabajadoresDisponiblesDTO.setCalificacion(trabajador.getReputacion());
            //trabajadoresDisponiblesDTO.setServicios_finalizados(serviciosRepo.countByUsuarioModelTrabajadorIdAndStatus(trabajador.getId_usuario(),16));
            trabajadoresDisponiblesDTO.setServicios_finalizados(serviciosRepo.countByUsuarioModelTrabajadorIdAndStatus(trabajador.getUsuarioModel().getId(),16));
            //trabajadoresDisponiblesDTO.setTotal_calificaciones(calificacionesRepo.countById_usuario(trabajador.getId_usuario()));
            trabajadoresDisponiblesDTO.setTotal_calificaciones(calificacionesRepo.countById_usuario(trabajador.getUsuarioModel().getId()));
            trabajadoresDisponiblesLista.add(trabajadoresDisponiblesDTO);
        }
        return trabajadoresDisponiblesLista;
    }

    public TrabajadorPerfilResponse mostrarPerfilTrabajador(int idUsuario){
        TrabajadoresModel trabajador = trabajadoresRepo.findTrabajadorByIdUsuario(idUsuario);
        int serviciosFinalizados = serviciosRepo.countByUsuarioModelTrabajadorIdAndStatus((long) idUsuario,17);
        List<CalificacionesModel> calificaciones = calificacionesRepo.findById_usuario((long) idUsuario);
        int totalCalificaciones = calificaciones.size();
        String descripcion = trabajador.getDescripcion() != null ? trabajador.getDescripcion() :"Sin Descripcion";
        String experiencia = trabajador.getExperiencia() != null ? trabajador.getExperiencia() :"Sin Experiencia";
        String tipoServicio ="Tipo de servicio no definido";
        if (trabajador.getTipoServicioModel() != null) {
            tipoServicio = trabajador.getTipoServicioModel().getDescripcion();
        }
        return TrabajadorPerfilResponse.builder()
                .nombre(trabajador.getUsuarioModel().getNombre())
                .descripcion(descripcion)
                .experiencia(experiencia)
                .tipo_servicio(tipoServicio)
                .reputacion(trabajador.getReputacion())
                .fecha_registro(trabajador.getFecha_registro())
                .servicios_finalizados(serviciosFinalizados)
                .total_calificaciones(totalCalificaciones)
                .build();
    }


}
