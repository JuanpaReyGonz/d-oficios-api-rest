package com.doficios.apirest.Cotizacion;

import com.doficios.apirest.Models.*;
import com.doficios.apirest.Repositories.*;
import com.doficios.apirest.Trabajadores.TrabajadoresDisponiblesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CotizacionService {
    @Autowired
    TipoServicioRepository tipoServicioRepo;
    @Autowired
    PreciosRepository preciosRepo;
    @Autowired
    SubServiciosRepository subServiciosRepo;
    //Para la búsqueda de trabajadores:
    @Autowired
    TrabajadoresRepository trabajadoresRepo;
    @Autowired
    ServiciosRepository serviciosRepo;
    @Autowired
    CalificacionesRepository calificacionesRepo;
    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    DireccionesRepository direccionesRepo;
    @Autowired
    HistorialStatusRepository historialRepo;
    @Autowired
    TareasPorServicioRepository tareasPorServicioRepo;
    @Transactional(readOnly = true)
    public CotizacionDTO obtenerCotizacion(@RequestBody CotizacionRequest request){
        //Obteniendo toda la lista de datos que se manda desde el request
        List<SubservicioListRequest> subserviciosRequest = request.getSubservicioList();
        List<SubserviciosCotizacionDTO> subserviciosCotizacionDTOList = new ArrayList<>();
        double total=0;
        //Definiendo cotizacion
        for (SubservicioListRequest subservicio: subserviciosRequest ) {
            String descripcionSubservicio = subServiciosRepo.findDescripcionSubservicioByTipoAndId(request.getTipo_servicio(),subservicio.getId_subservicio());
            //System.out.println("Tipo Servicio: "+request.getTipo_servicio());
            PreciosModel precio = preciosRepo.findByTipoServicioModelIdAndSubServiciosModelId(request.getTipo_servicio(), subservicio.getId_subservicio());
            SubserviciosCotizacionDTO subserviciosCotizacion = new SubserviciosCotizacionDTO();
            subserviciosCotizacion.setId_subservicio(subservicio.getId_subservicio());
            //System.out.println("Subservicio: "+subserviciosCotizacion.getId_subservicio());
            subserviciosCotizacion.setSubservicio(descripcionSubservicio);
            //System.out.println("Descripcion: "+subserviciosCotizacion.getSubservicio());
            subserviciosCotizacion.setCantidad(subservicio.getCantidad());
            //System.out.println("Cantidad: "+subserviciosCotizacion.getCantidad());
            subserviciosCotizacion.setPrecio_unitario((precio.getMinimo()+ precio.getMaximo())/2);
            //System.out.println("Precio_unitario"+subserviciosCotizacion.getPrecio_unitario());
            subserviciosCotizacion.setIva(subserviciosCotizacion.getPrecio_unitario()*.16);
            //System.out.println("IVA: "+subserviciosCotizacion.getIva());
            subserviciosCotizacion.setSubtotal((subserviciosCotizacion.getPrecio_unitario()+subserviciosCotizacion.getIva())*subserviciosCotizacion.getCantidad());
            //System.out.println("subtotal: "+subserviciosCotizacion.getSubtotal());
            subserviciosCotizacionDTOList.add(subserviciosCotizacion);
            total += subserviciosCotizacion.getSubtotal();
        }
        //Buscando Trabajadores
        List<TrabajadoresDisponiblesDTO> trabajadoresDisponiblesList = new ArrayList<>();

        List<TrabajadoresModel> trabajadores = trabajadoresRepo.findFirst3TrabajadoresByTipoServicio(request.getTipo_servicio(),request.getFecha_servicio());
        System.out.println("La fecha de servicio es: "+ request.getFecha_servicio());

        for (TrabajadoresModel trabajador : trabajadores){
            TrabajadoresDisponiblesDTO trabajadoresDisponiblesDTO = new TrabajadoresDisponiblesDTO();
            //trabajadoresDisponiblesDTO.setId_trabajador(trabajador.getId_usuario());
            trabajadoresDisponiblesDTO.setId_trabajador(trabajador.getUsuarioModel().getId());
            trabajadoresDisponiblesDTO.setNombre(trabajador.getUsuarioModel().getNombre());
            trabajadoresDisponiblesDTO.setCalificacion(trabajador.getReputacion());
            //trabajadoresDisponiblesDTO.setServicios_finalizados(serviciosRepo.countByUsuarioModelTrabajadorIdAndStatus(trabajador.getId_usuario(),16));
            trabajadoresDisponiblesDTO.setServicios_finalizados(serviciosRepo.countByUsuarioModelTrabajadorIdAndStatus(trabajador.getUsuarioModel().getId(),17));
            //trabajadoresDisponiblesDTO.setTotal_calificaciones(calificacionesRepo.countById_usuario(trabajador.getId_usuario()));
            trabajadoresDisponiblesDTO.setTotal_calificaciones(calificacionesRepo.countById_usuario(trabajador.getUsuarioModel().getId()));
            trabajadoresDisponiblesDTO.setPrioridad(0);
            trabajadoresDisponiblesList.add(trabajadoresDisponiblesDTO);
        }


        return CotizacionDTO.builder()
                .id_tiposervicio(request.getTipo_servicio())
                .tipo_servicio(tipoServicioRepo.findById_Tiposervicio(request.getTipo_servicio()))
                .total(total)
                .comision(total*.10)
                .fecha_servicio(request.getFecha_servicio())
                .hora_servicio(request.getHora_servicio())
                .id_direccion(request.getId_direccion())
                .subservicioList(subserviciosCotizacionDTOList)
                .trabajadorList(trabajadoresDisponiblesList)
                .build();
    }

    @Transactional
    public ConfirmarCotizacionResponse confirmarCotizacion(@RequestBody CotizacionDTO request, String username){
        Long idUsuario = (long) usuarioRepo.findByCorreo(username);
        //Obteniendo información de la direccion del usuario.
        DireccionesModel direccion = direccionesRepo.findById_UsuarioAndId_Direccion(idUsuario, request.getId_direccion());
        //Obteniendo listado de trabajadores y el prioritario
        Long trabajadorSeleccionado = null;
        List<TrabajadoresDisponiblesDTO> trabajadores = request.trabajadorList;
        for (TrabajadoresDisponiblesDTO trabajador: trabajadores){
            if(trabajador.getPrioridad() == 1){
                trabajadorSeleccionado = trabajador.getId_trabajador();
            }
        }
        //Formateando las fechas
        DateTimeFormatter formatFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatHora = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate fechaServicio = LocalDate.parse(request.getFecha_servicio(), formatFecha);
        LocalTime horaServicio = LocalTime.parse(request.getHora_servicio(), formatHora);
        //Obteniendo fecha actual con zona horaria GMT-6
        ZoneId gmtMinus6 = ZoneId.of("GMT-6");
        ZonedDateTime nowInGmtMinus6 = ZonedDateTime.now(gmtMinus6);
        LocalDateTime fechaGrabadoServicio = nowInGmtMinus6.toLocalDateTime();

        //Insertando en tabla de servicios
         serviciosRepo.insertarServicio(idUsuario,trabajadorSeleccionado,2, fechaGrabadoServicio, fechaServicio,horaServicio,
                request.getTotal(),request.getComision(), request.getId_tiposervicio(), direccion.getEntidad(),direccion.getMunicipio(),
                direccion.getLocalidad(),direccion.getDomicilio(),direccion.getExterior(), direccion.getInterior(), direccion.getColonia(), direccion.getCp());
         Long idServicio = serviciosRepo.getLastInsertedId();
         System.out.println("Se inserto el servicio "+idServicio);

        //Insertando en candidatos_servicio
        for (TrabajadoresDisponiblesDTO trabajador: trabajadores) {
            char status = (trabajador.getPrioridad() == 1) ? 'P' : 'N';
            trabajadoresRepo.insertarCandidatoServicio(idServicio,trabajador.getId_trabajador(),status,trabajador.getPrioridad(),1);
            System.out.println("Se inserto en candidatos_servicio");
        }

        //Insertando en historial_status
        historialRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,idUsuario,1,fechaGrabadoServicio);
        historialRepo.insertHistorialStatusByIdServicioAndIdUsuarioAndIdStatus(idServicio,trabajadorSeleccionado,2,fechaGrabadoServicio);
        System.out.println("Se inserto en historial_status");

        //Insertando en tareas_servicio
        int idTarea=1;
        List<SubserviciosCotizacionDTO> subservicios = request.subservicioList;
        for (SubserviciosCotizacionDTO subservicio : subservicios){
            tareasPorServicioRepo.insertTareasServicio(idTarea,idServicio,subservicio.getPrecio_unitario(),subservicio.getIva(),1,
                    subservicio.getId_subservicio(),subservicio.getCantidad(),subservicio.getSubtotal());
            System.out.println("Se inserto la tarea "+idTarea +" del servicio: "+idServicio);
        idTarea++;
        }

        return ConfirmarCotizacionResponse.builder()
                .id_servicio(idServicio)
                .build();
    }
}
