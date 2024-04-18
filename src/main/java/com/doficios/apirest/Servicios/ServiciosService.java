package com.doficios.apirest.Servicios;

import com.doficios.apirest.Models.CalificacionesModel;
import com.doficios.apirest.Oficios.SubServiciosRepository;
import com.doficios.apirest.Repositories.CalificacionesRepository;
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

    public List<TarjetasSolicitudesClienteDTO> obtenerTarjetasSolicitudesCliente(String username) {
        DecimalFormat df = new DecimalFormat("0.00"); //Formatear importe siempre a 2 decimales.

       //List<ServiciosModel> servicios = serviciosRepo.findAll();
        List<ServiciosModel> servicios = serviciosRepo.findByUsuarioModelCorreo(username);
        List<TarjetasSolicitudesClienteDTO> tarjetasDTO = new ArrayList<>();
        for (ServiciosModel servicio : servicios) {
            TarjetasSolicitudesClienteDTO dto = new TarjetasSolicitudesClienteDTO();
            dto.setId_servicio(servicio.getId_servicio());
            dto.setTipo_servicio(servicio.getTipoServicioModel().getDescripcion());
            dto.setStatus(servicio.getStatusModel().getDescripcion());
            dto.setFecha_solicitud(servicio.getFecha_solicitud().substring(0,servicio.getFecha_solicitud().length()-3));
            String importeFormateado = df.format(servicio.getImporte());
            dto.setImporte(new BigDecimal(importeFormateado));
            tarjetasDTO.add(dto);
        }
        return tarjetasDTO;
    }

    /*@Transactional(readOnly = true)
    public List<ServiciosModel> obtenerDetalleServicio(Integer idServicio) {
        List<ServiciosModel> servicioGenerales = serviciosRepo.findById_servicio(idServicio);
        List<TareasPorServicioModel> tareasServicio = tareasRepo.findByIdServicio(idServicio);

        return servicioGenerales;

    }*/
    @Transactional(readOnly = true)
    public DetallePorServicioDTO obtenerDetalleServicio(Integer idServicio) {
        ServiciosModel servicioGenerales = serviciosRepo.findById_servicio(idServicio);
        List<TareasPorServicioModel> tareasServicio = tareasRepo.findByIdServicio(idServicio);
        List<CalificacionesModel> calificaciones = calificacionesRepo.findById_usuario(servicioGenerales.getUsuarioModelTrabajador().getId());
        int totalCalificaciones = calificaciones.size();
        double sumaCalificaciones=0;
        for (CalificacionesModel calificacion : calificaciones){
            sumaCalificaciones+=calificacion.getCalificacion();
            System.out.println("id_usuario: "+calificacion.getId_usuario());
            System.out.println("calificacion: "+calificacion.getCalificacion());
            }
        List<SubServiciosListaDTO> subServiciosLista = new ArrayList<>();

        for (TareasPorServicioModel tarea: tareasServicio){
            SubServiciosListaDTO subServicio = new SubServiciosListaDTO();
            subServicio.setSubservicio(subServiciosRepo.findDescripcionSubservicioByTipoAndId(servicioGenerales.getTipoServicioModel().getId_tiposervicio(),tarea.getSub_servicio()));
            subServicio.setPrecio_unitario(tarea.getImporte());
            subServicio.setCantidad(tarea.getUnidad());
            subServicio.setIva(tarea.getIva());
            subServicio.setSubtotal(tarea.getTotal());
            /*System.out.println("---------------------------");
            System.out.println("id_tarea: "+tarea.getIdTarea());
            System.out.println("id_servicio:"+tarea.getIdServicio());
            System.out.println("importe: "+tarea.getImporte());
            System.out.println("iva: "+tarea.getIva());
            System.out.println("unidad: "+tarea.getUnidad());
            System.out.println("total: "+tarea.getTotal());*/

            subServiciosLista.add(subServicio);
        }

        List<StatusHistoricoDTO> statusHistoricoLista = new ArrayList<>();
        List<HistorialStatusModel> historialStatusModel = historialStatusRepo.findByIdServicioAndIdUsuarioAndStatus(idServicio,servicioGenerales.getUsuarioModel().getId(),2);
        for (HistorialStatusModel status : historialStatusModel){
            StatusHistoricoDTO historicoDTO = new StatusHistoricoDTO();
            historicoDTO.setStatus(status.getStatusModel().getDescripcion());
            historicoDTO.setFecha(status.getFecha());

            statusHistoricoLista.add(historicoDTO);
        }


        return DetallePorServicioDTO.builder()
                .id_servicio(servicioGenerales.getId_servicio())
                .tipo_servicio(servicioGenerales.getTipoServicioModel().getDescripcion())
                .num_status(servicioGenerales.getStatusModel().getStatus())
                .status(servicioGenerales.getStatusModel().getDescripcion())
                .fecha_solicitud(servicioGenerales.getFecha_solicitud())
                .total(servicioGenerales.getImporte())
                .comision(servicioGenerales.getComision())
                .nombre_trabajador(servicioGenerales.getUsuarioModelTrabajador().getNombre())
                .promedio_estrellas(sumaCalificaciones/totalCalificaciones)
                .total_calificaciones(totalCalificaciones)
                .subservicios_lista(subServiciosLista)
                .status_historico(statusHistoricoLista)
                .build();

    }


}
