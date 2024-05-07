package com.doficios.apirest.Calificaciones;

import com.doficios.apirest.Models.CalificacionesModel;
import com.doficios.apirest.Models.ServiciosModel;
import com.doficios.apirest.Repositories.CalificacionesRepository;
import com.doficios.apirest.Repositories.ServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CalificacionService {
    @Autowired
    CalificacionesRepository calificacionesRepo;
    @Autowired
    ServiciosRepository serviciosRepo;
    @Transactional
    public CalificacionResponse guardarCalificacionCliente(@RequestBody CalificacionClienteRequest request, String username){
        ServiciosModel servicioGenerales = serviciosRepo.findById_servicio(request.getId_servicio());
        Long idTrabajador = servicioGenerales.getUsuarioModelTrabajador().getId();
        //Grabando en calificaciones
        CalificacionesModel calificacion = new CalificacionesModel();
        calificacion.setId_usuario(idTrabajador);
        calificacion.setId_servicio(Long.valueOf(request.getId_servicio()));
        calificacion.setCalificacion(request.getCalificacion_usuario());
        calificacion.setComentario(request.getComentario_usuario());
        calificacionesRepo.save(calificacion);
        //Actualizando en la calificacion del servicio
        serviciosRepo.actualizaCalificacionServicioByIdServicio(request.getCalificacion_servicio(), request.getComentario_servicio(), Long.valueOf(request.getId_servicio()));

        return CalificacionResponse.builder()
                .id_servicio(Long.valueOf(request.getId_servicio()))
                .build();
    }

    @Transactional
    public CalificacionResponse guardarCalificacionTrabajador(@RequestBody CalificacionTrabajadorRequest request, String username){
        ServiciosModel servicioGenerales = serviciosRepo.findById_servicio(request.getId_servicio());
        Long idCliente = servicioGenerales.getUsuarioModel().getId();
        //Grabando en calificaciones
        CalificacionesModel calificacion = new CalificacionesModel();
        calificacion.setId_usuario(idCliente);
        calificacion.setId_servicio(Long.valueOf(request.getId_servicio()));
        calificacion.setCalificacion(request.getCalificacion_usuario());
        calificacion.setComentario(request.getComentario_usuario());
        calificacionesRepo.save(calificacion);

        return  CalificacionResponse.builder()
                .id_servicio(Long.valueOf(request.getId_servicio()))
                .build();
    }
}