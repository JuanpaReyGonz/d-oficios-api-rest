package com.doficios.apirest.Status;

import com.doficios.apirest.Models.ServiciosModel;
import com.doficios.apirest.Repositories.HistorialStatusRepository;
import com.doficios.apirest.Repositories.ServiciosRepository;
import com.doficios.apirest.Repositories.TareasPorServicioRepository;
import com.doficios.apirest.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
@Service
public class UpdateStatusService {
    /*@Autowired
    UsuarioRepository usuarioRepo;*/
    @Autowired
    ServiciosRepository serviciosRepo;
    @Autowired
    HistorialStatusRepository historialStatusRepo;
    @Autowired
    TareasPorServicioRepository tareasRepo;

    @Transactional
    public UpdateStatusResponse actualizarStatus(@RequestBody UpdateStatusRequest request, String username){
        //Long idUsuario = (long) usuarioRepo.findByCorreo(username);
        Long idServicio = (long) request.getId_servicio();
        ServiciosModel servicio = serviciosRepo.findById_servicio(request.getId_servicio());
        Long cliente = servicio.getUsuarioModel().getId();
        Long trabajador = servicio.getUsuarioModelTrabajador().getId();

        ZoneId gmtMinus6 = ZoneId.of("GMT-6");
        ZonedDateTime nowInGmtMinus6 = ZonedDateTime.now(gmtMinus6);
        LocalDateTime fechaActual = nowInGmtMinus6.toLocalDateTime();
        int status = request.getStatus();
        if (status == 3){
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,3,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,5,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,3,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,4,fechaActual);
            serviciosRepo.actualizaStatusServicio(idServicio,5);
        } else if (status == 6) {
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,6,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,7,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,8,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,8,fechaActual);
            serviciosRepo.actualizaStatusServicio(idServicio,8);
        } else if (status == 11) {
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,11,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,11,fechaActual);
            serviciosRepo.actualizaStatusServicio(idServicio,11);
        } else if (status == 17) {
            //historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,12,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,17,fechaActual);
            //historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,12,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,17,fechaActual);
            serviciosRepo.actualizaStatusServicio(idServicio,17);
            tareasRepo.actualizaStatusTareas(idServicio,3);
        }


        return UpdateStatusResponse.builder()
                .id_servicio(idServicio)
                .build();
    }
}
