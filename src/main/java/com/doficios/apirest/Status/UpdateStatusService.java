package com.doficios.apirest.Status;

import com.doficios.apirest.Models.CandidatoServicioModel;
import com.doficios.apirest.Models.ServiciosModel;
import com.doficios.apirest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
@Service
public class UpdateStatusService {
    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    ServiciosRepository serviciosRepo;
    @Autowired
    HistorialStatusRepository historialStatusRepo;
    @Autowired
    TareasPorServicioRepository tareasRepo;
    @Autowired
    CandidatoServicioRepository candidatoServicioRepo;

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
        int prioridadCandidato;
        int status = request.getStatus();
        if (status == 3){
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,3,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,5,fechaActual.plusSeconds(1));
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,3,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,4,fechaActual.plusSeconds(1));
            prioridadCandidato = candidatoServicioRepo.findPrioridadByIdServicioAndIdTrabajador(idServicio,trabajador);
            candidatoServicioRepo.actualizaStatusCandidatoByIdServicioAndIdTrabajador('A',idServicio,trabajador,prioridadCandidato);
            serviciosRepo.actualizaStatusServicio(idServicio,5);
        } else if (status == 6) {
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,6,fechaActual);
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,7,fechaActual.plusSeconds(1));
            historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,8,fechaActual.plusSeconds(1));
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
        } else if (status == 19) {
            if (usuarioRepo.findTipoUsuarioByCorreo(username) == 'C'){
                //Flujo si el cliente cancela
                System.out.println("Es el cliente quien está cancelando");
                historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,19,fechaActual);
                historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,19,fechaActual);
                serviciosRepo.actualizaStatusServicio(idServicio,19);
                tareasRepo.actualizaStatusTareas(idServicio,4);
            } else if (usuarioRepo.findTipoUsuarioByCorreo(username) == 'T') {
                prioridadCandidato = candidatoServicioRepo.findPrioridadByIdServicioAndIdTrabajador(idServicio,trabajador);
                //Si es el último de los 3 trabajadores candidatos, cancelar completamente el servicio.
                if(prioridadCandidato == 3){
                    System.out.println("Esta cancelando el tercer trabajador, se cancela a status 19 en historial y servicio");
                    historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,0,fechaActual);
                    historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,cliente,19,fechaActual.plusSeconds(1));
                    historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,19,fechaActual.plusSeconds(1));
                    candidatoServicioRepo.actualizaStatusCandidatoByIdServicioAndIdTrabajador('R',idServicio,trabajador,prioridadCandidato);
                    serviciosRepo.actualizaStatusServicio(idServicio,19);
                    tareasRepo.actualizaStatusTareas(idServicio,4);
                } else{
                    //Si no, solamente actualizar estatus.
                    System.out.println("Cancelo el candidato del servicio "+idServicio+" de estatus: "+prioridadCandidato);
                    historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajador,19,fechaActual);
                    candidatoServicioRepo.actualizaStatusCandidatoByIdServicioAndIdTrabajador('R',idServicio,trabajador,prioridadCandidato);
                    //Buscar al siguiente en la lista y actualizar estatus
                    prioridadCandidato++;
                    System.out.println("Ahora se busca al candidato siguiente con prioridad "+prioridadCandidato+" del servicio: "+idServicio);
                    CandidatoServicioModel nuevoCandidato = candidatoServicioRepo.findCandidatoByIdServicioAndPrioridad(idServicio,prioridadCandidato);
                    candidatoServicioRepo.actualizaStatusCandidatoByIdServicioAndIdTrabajador('P',idServicio, nuevoCandidato.getId_trabajador(), prioridadCandidato);
                    //Actualizar servicio
                    serviciosRepo.actualizaTrabajadorServicio(nuevoCandidato.getId_trabajador(), idServicio);
                    //Actualizar historial_status con nuevo usuario
                    historialStatusRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,nuevoCandidato.getId_trabajador(),2,fechaActual);
                }


            }

        }


        return UpdateStatusResponse.builder()
                .id_servicio(idServicio)
                .build();
    }
}
