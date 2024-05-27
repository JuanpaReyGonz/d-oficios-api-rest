package com.doficios.apirest.Servicios;

import com.doficios.apirest.Models.CalificacionesModel;
import com.doficios.apirest.Models.HistorialStatusModel;
import com.doficios.apirest.Models.ServiciosModel;
import com.doficios.apirest.Models.TareasPorServicioModel;
import com.doficios.apirest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiciosService {
    @Autowired
    ServiciosRepository serviciosRepo;
    @Autowired
    TareasPorServicioRepository tareasRepo;
    @Autowired
    CalificacionesRepository calificacionesRepo;
    @Autowired
    SubServiciosRepository subServiciosRepo;
    @Autowired
    HistorialStatusRepository historialStatusRepo;
    @Autowired
    EntidadRepository entidadRepo;
    @Autowired
    MunicipioRepository municipioRepo;
    @Autowired
    LocalidadRepository localidadRepo;
    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    TrabajadoresRepository trabajadoresRepo;

    public List<TarjetasSolicitudesClienteDTO> obtenerTarjetasSolicitudesCliente(String username) {
        DecimalFormat df = new DecimalFormat("0.00"); //Formatear importe siempre a 2 decimales.
        //Obtener el ID del usuario a partir del correo.
        char tipoUsuario = usuarioRepo.findTipoUsuarioByCorreo(username);
        Long idUsuario = (long) usuarioRepo.findByCorreo(username);
        List<ServiciosModel> servicios = null;
        if(tipoUsuario=='T'){
            servicios = serviciosRepo.findByUsuarioModelTrabajadorCorreo(username);
        } else if (tipoUsuario =='C') {
            servicios = serviciosRepo.findByUsuarioModelCorreo(username);
        }
        
        List<TarjetasSolicitudesClienteDTO> tarjetasDTO = new ArrayList<>();
        for (ServiciosModel servicio : servicios) {
            HistorialStatusModel status = historialStatusRepo.findLastStatusByIdServicioAndIdUsuario(servicio.getId_servicio(),idUsuario);
            TarjetasSolicitudesClienteDTO dto = new TarjetasSolicitudesClienteDTO();
            dto.setId_servicio(servicio.getId_servicio());
            dto.setTipo_servicio(servicio.getTipoServicioModel().getDescripcion());
            dto.setId_status(status.getStatusModel().getStatus());
            dto.setStatus(status.getStatusModel().getDescripcion());
            dto.setFecha_solicitud(servicio.getFecha_solicitud().substring(0,servicio.getFecha_solicitud().length()-3));
            String importeFormateado = null;
            if (tipoUsuario == 'C'){
                importeFormateado = df.format(servicio.getImporte()+servicio.getComision());
            } else if (tipoUsuario == 'T') {
                importeFormateado = df.format(servicio.getImporte());
            }
            assert importeFormateado != null;
            dto.setImporte(new BigDecimal(importeFormateado));
            tarjetasDTO.add(dto);
        }
        return tarjetasDTO;
    }
    @Transactional(readOnly = true)
    public DetallePorServicioDTO obtenerDetalleServicio(Integer idServicio, String username) {
        //Revisar por ID
        Long idUsuario = (long) usuarioRepo.findByCorreo(username);

        DecimalFormat df = new DecimalFormat("0.00"); //Formatear importe siempre a 2 decimales.
        ServiciosModel servicioGenerales = serviciosRepo.findById_servicio(idServicio);

        //Obtener el ID cliente y ID trabajador
        Long idTrabajador;
        String nombreTrabajador;
        double promedioEstrellasTrabajador=0;
        try {
            idTrabajador = servicioGenerales.getUsuarioModelTrabajador().getId();
            nombreTrabajador = servicioGenerales.getUsuarioModelTrabajador().getNombre();
            promedioEstrellasTrabajador=trabajadoresRepo.findTrabajadorByIdUsuario(Math.toIntExact(idTrabajador)).getReputacion();
            //System.out.println("Promedio de estrellas del trabajador: "+promedioEstrellasTrabajador);
        } catch (NullPointerException ex) {
            idTrabajador = 0L;
            nombreTrabajador = "";
        }

        Long idCliente = servicioGenerales.getUsuarioModel().getId();

        List<TareasPorServicioModel> tareasServicio = tareasRepo.findByIdServicio(idServicio);
        //List<CalificacionesModel> calificaciones = calificacionesRepo.findById_usuario(servicioGenerales.getUsuarioModelTrabajador().getId());
        List<CalificacionesModel> calificaciones = calificacionesRepo.findById_usuario(idTrabajador);
        List<CalificacionesModel> calificacionesHistoricoCliente = calificacionesRepo.findById_usuario(idCliente);
        String entidad = entidadRepo.findByIdEntidad(servicioGenerales.getEntidad());
        String municipio = municipioRepo.findByIdEntidadAndIdMunicipio(servicioGenerales.getEntidad(),servicioGenerales.getMunicipio());
        String localidad = localidadRepo.findByIdEntidadAndIdMunicipioAndIdLocalidad(servicioGenerales.getEntidad(),servicioGenerales.getMunicipio(),servicioGenerales.getLocalidad());
        String lugarServicio = servicioGenerales.getDomicilio() +" " +servicioGenerales.getExterior() + " " +servicioGenerales.getInterior()
                + " " + servicioGenerales.getColonia() + " "+ servicioGenerales.getCp() + ", "+ localidad+", "+municipio+", "+entidad;
        String telefonoCliente = usuarioRepo.findTelByIdUsuario(idCliente);
        String telefonoTrabajador = usuarioRepo.findTelByIdUsuario(idTrabajador);
        //Calificaciones de Cliente y Trabajador.
        CalificacionesModel calificacionServicioCliente = calificacionesRepo.findByIdUsuarioAndIdServicio(idCliente,idServicio);
        CalificacionesModel calificacionServicioTrabajador = calificacionesRepo.findByIdUsuarioAndIdServicio(idTrabajador,idServicio);
        int calificacionServicio = (servicioGenerales.getCalificacion() != null) ? servicioGenerales.getCalificacion() : 0;

        int calificacionCliente = 0;
        String calificacionComentarioCliente = null;
        if(calificacionServicioCliente != null){
          calificacionCliente = calificacionServicioCliente.getCalificacion();
          calificacionComentarioCliente = calificacionServicioCliente.getComentario();
        }
        int calificacionTrabajador = 0;
        String calificacionComentarioTrabajador = null;
        if(calificacionServicioTrabajador != null){
          calificacionTrabajador = calificacionServicioTrabajador.getCalificacion();
          calificacionComentarioTrabajador = calificacionServicioTrabajador.getComentario();
        }
        //Obteniendo histórico de cliente
        int totalCalificacionesCliente = calificacionesHistoricoCliente.size();
        //System.out.println("Total de calificaciones del cliente: "+totalCalificacionesCliente);
        double sumaCalificacionesHistoricoCliente=0;
        for (CalificacionesModel calificacion : calificacionesHistoricoCliente){
            sumaCalificacionesHistoricoCliente+=calificacion.getCalificacion();
        }
        double promedioEstrellasCliente = Math.round((sumaCalificacionesHistoricoCliente/totalCalificacionesCliente)*10.0)/10.0;
        //System.out.println("Promedio de calificaciones del cliente: " + promedioEstrellasCliente);

        //Obteniendo histórico de trabajador
        int totalCalificaciones = calificaciones.size();
        //System.out.println("Total de calificaciones del trabajador: "+totalCalificaciones);
        List<SubServiciosListaDTO> subServiciosLista = new ArrayList<>();

        for (TareasPorServicioModel tarea: tareasServicio){
            String tareaPrecioUnitFormateado = df.format(tarea.getImporte());
            String tareaTotalFormateado = df.format(tarea.getTotal()); //Mapear a 2 decimales
            String tareaIvaFormateado = df.format(tarea.getIva());

            SubServiciosListaDTO subServicio = new SubServiciosListaDTO();
            subServicio.setSubservicio(subServiciosRepo.findDescripcionSubservicioByTipoAndId(servicioGenerales.getTipoServicioModel().getId_tiposervicio(),tarea.getSub_servicio()));
            subServicio.setPrecio_unitario(new BigDecimal(tareaPrecioUnitFormateado));
            subServicio.setCantidad(tarea.getUnidad());
            subServicio.setIva(new BigDecimal(tareaIvaFormateado));
            subServicio.setSubtotal(new BigDecimal(tareaTotalFormateado));
            subServiciosLista.add(subServicio);
        }

        List<StatusHistoricoDTO> statusHistoricoLista = new ArrayList<>();
        List<HistorialStatusModel> historialStatusModel = historialStatusRepo.findByIdServicioAndIdUsuario(idServicio,idUsuario);
        for (HistorialStatusModel status : historialStatusModel){
            StatusHistoricoDTO historicoDTO = new StatusHistoricoDTO();
            historicoDTO.setNum_status(status.getStatusModel().getStatus());
            historicoDTO.setStatus(status.getStatusModel().getDescripcion());
            historicoDTO.setFecha(status.getFecha());

            statusHistoricoLista.add(historicoDTO);
        }
        //HistorialStatusModel statusActual = historialStatusRepo.findLastStatusByIdServicioAndIdUsuario(Long.valueOf(idServicio),servicioGenerales.getUsuarioModel().getId());
        HistorialStatusModel statusActual = historialStatusRepo.findLastStatusByIdServicioAndIdUsuario(Long.valueOf(idServicio),idUsuario);
        String totalFormateado = df.format(servicioGenerales.getImporte()); //Mapear a 2 decimales
        String comisionFormateado = df.format(servicioGenerales.getComision()); //Mapeas a 2 decimales
        return DetallePorServicioDTO.builder()
                .id_servicio(servicioGenerales.getId_servicio())
                .tipo_servicio(servicioGenerales.getTipoServicioModel().getDescripcion())
                .num_status(statusActual.getStatusModel().getStatus())
                .status(statusActual.getStatusModel().getDescripcion())
                .fecha_solicitud(servicioGenerales.getFecha_solicitud())
                .fecha_servicio(servicioGenerales.getFecha_servicio())
                .hora_servicio(servicioGenerales.getHora_servicio())
                .lugar_servicio(lugarServicio)
                .total(new BigDecimal(totalFormateado))
                .comision(new BigDecimal(comisionFormateado))
                .nombre_trabajador(nombreTrabajador)
                .promedio_estrellas(promedioEstrellasTrabajador)
                .total_calificaciones(totalCalificaciones)
                .calificacion_servicio(calificacionServicio)
                .comentario_servicio(servicioGenerales.getComentario())
                .id_trabajador(idTrabajador)
                .telefono_trabajador(telefonoTrabajador)
                .calificacion_trabajador(calificacionTrabajador)
                .comentario_trabajador(calificacionComentarioTrabajador)
                .servicios_finalizados_trabajador(serviciosRepo.countByUsuarioModelTrabajadorIdAndStatus(idTrabajador,17))
                .nombre_cliente(servicioGenerales.getUsuarioModel().getNombre())
                .telefono_cliente(telefonoCliente)
                .calificacion_cliente(calificacionCliente)
                .comentario_cliente(calificacionComentarioCliente)
                .total_calificaciones_cliente(totalCalificacionesCliente)
                //.promedio_estrellas_cliente(sumaCalificacionesHistoricoCliente/totalCalificacionesCliente)
                .promedio_estrellas_cliente(promedioEstrellasCliente)
                .servicios_finalizados_cliente(serviciosRepo.countByUsuarioModelIdAndStatus(idCliente,17))
                .subservicios_lista(subServiciosLista)
                .status_historico(statusHistoricoLista)
                .build();

    }


}
