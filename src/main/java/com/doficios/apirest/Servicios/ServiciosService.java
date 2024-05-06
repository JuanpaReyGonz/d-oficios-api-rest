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

    public List<TarjetasSolicitudesClienteDTO> obtenerTarjetasSolicitudesCliente(String username) {
        DecimalFormat df = new DecimalFormat("0.00"); //Formatear importe siempre a 2 decimales.
        //Obtener el ID del usuario a partir del correo.
        char tipoUsuario = usuarioRepo.findTipoUsuarioByCorreo(username);
        Long idUsuario = (long) usuarioRepo.findByCorreo(username);
        //System.out.println(historialStatusRepo.findLastStatusByIdServicioAndIdUsuario(1,1L).getFecha());
       //List<ServiciosModel> servicios = serviciosRepo.findAll();
        List<ServiciosModel> servicios = null;
        if(tipoUsuario=='T'){
            servicios = serviciosRepo.findByUsuarioModelTrabajadorCorreo(username);
        } else if (tipoUsuario =='C') {
            servicios = serviciosRepo.findByUsuarioModelCorreo(username);
        }
        
        List<TarjetasSolicitudesClienteDTO> tarjetasDTO = new ArrayList<>();
        for (ServiciosModel servicio : servicios) {
            //System.out.println("Id servicio: "+servicio.getId_servicio());
            //System.out.println("Id Usuario: " + idUsuario);
            HistorialStatusModel status = historialStatusRepo.findLastStatusByIdServicioAndIdUsuario(servicio.getId_servicio(),idUsuario);
            //System.out.println(status.toString());
            TarjetasSolicitudesClienteDTO dto = new TarjetasSolicitudesClienteDTO();
            dto.setId_servicio(servicio.getId_servicio());
            dto.setTipo_servicio(servicio.getTipoServicioModel().getDescripcion());
            //dto.setStatus(servicio.getStatusModel().getDescripcion());
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
    public DetallePorServicioDTO obtenerDetalleServicio(Integer idServicio) {
        DecimalFormat df = new DecimalFormat("0.00"); //Formatear importe siempre a 2 decimales.
        ServiciosModel servicioGenerales = serviciosRepo.findById_servicio(idServicio);
        List<TareasPorServicioModel> tareasServicio = tareasRepo.findByIdServicio(idServicio);
        List<CalificacionesModel> calificaciones = calificacionesRepo.findById_usuario(servicioGenerales.getUsuarioModelTrabajador().getId());
        String entidad = entidadRepo.findByIdEntidad(servicioGenerales.getEntidad());
        String municipio = municipioRepo.findByIdEntidadAndIdMunicipio(servicioGenerales.getEntidad(),servicioGenerales.getMunicipio());
        String localidad = localidadRepo.findByIdEntidadAndIdMunicipioAndIdLocalidad(servicioGenerales.getEntidad(),servicioGenerales.getMunicipio(),servicioGenerales.getLocalidad());
        String lugarServicio = servicioGenerales.getDomicilio() +" " +servicioGenerales.getExterior() + " " +servicioGenerales.getInterior()
                + " " + servicioGenerales.getColonia() + " "+ servicioGenerales.getCp() + ", "+ localidad+", "+municipio+", "+entidad;
        int totalCalificaciones = calificaciones.size();
        double sumaCalificaciones=0;
        for (CalificacionesModel calificacion : calificaciones){
            sumaCalificaciones+=calificacion.getCalificacion();
            /*System.out.println("id_usuario: "+calificacion.getId_usuario());
            System.out.println("calificacion: "+calificacion.getCalificacion());*/
            }
        List<SubServiciosListaDTO> subServiciosLista = new ArrayList<>();

        for (TareasPorServicioModel tarea: tareasServicio){
            String tareaPrecioUnitFormateado = df.format(tarea.getImporte());
            String tareaTotalFormateado = df.format(tarea.getTotal()); //Mapear a 2 decimales
            String tareaIvaFormateado = df.format(tarea.getIva());

            SubServiciosListaDTO subServicio = new SubServiciosListaDTO();
            subServicio.setSubservicio(subServiciosRepo.findDescripcionSubservicioByTipoAndId(servicioGenerales.getTipoServicioModel().getId_tiposervicio(),tarea.getSub_servicio()));
            //subServicio.setPrecio_unitario(tarea.getImporte());
            subServicio.setPrecio_unitario(new BigDecimal(tareaPrecioUnitFormateado));
            subServicio.setCantidad(tarea.getUnidad());
            subServicio.setIva(new BigDecimal(tareaIvaFormateado));
            subServicio.setSubtotal(new BigDecimal(tareaTotalFormateado));
            //subServicio.setSubtotal(tarea.getTotal());
            subServiciosLista.add(subServicio);
        }

        List<StatusHistoricoDTO> statusHistoricoLista = new ArrayList<>();
        List<HistorialStatusModel> historialStatusModel = historialStatusRepo.findByIdServicioAndIdUsuario(idServicio,servicioGenerales.getUsuarioModel().getId());
        for (HistorialStatusModel status : historialStatusModel){
            StatusHistoricoDTO historicoDTO = new StatusHistoricoDTO();
            //System.out.println("status actual: "+status.getStatusModel().getStatus());
            historicoDTO.setNum_status(status.getStatusModel().getStatus());
            historicoDTO.setStatus(status.getStatusModel().getDescripcion());
            historicoDTO.setFecha(status.getFecha());

            statusHistoricoLista.add(historicoDTO);
        }
        HistorialStatusModel statusActual = historialStatusRepo.findLastStatusByIdServicioAndIdUsuario(Long.valueOf(idServicio),servicioGenerales.getUsuarioModel().getId());
        String totalFormateado = df.format(servicioGenerales.getImporte()); //Mapear a 2 decimales
        String comisionFormateado = df.format(servicioGenerales.getComision()); //Mapeas a 2 decimales
        return DetallePorServicioDTO.builder()
                .id_servicio(servicioGenerales.getId_servicio())
                .tipo_servicio(servicioGenerales.getTipoServicioModel().getDescripcion())
                //.num_status(servicioGenerales.getStatusModel().getStatus())
                .num_status(statusActual.getStatusModel().getStatus())
                //.status(servicioGenerales.getStatusModel().getDescripcion())
                .status(statusActual.getStatusModel().getDescripcion())
                .fecha_solicitud(servicioGenerales.getFecha_solicitud())
                .fecha_servicio(servicioGenerales.getFecha_servicio())
                .hora_servicio(servicioGenerales.getHora_servicio())
                .lugar_servicio(lugarServicio)
                //.total(servicioGenerales.getImporte())
                .total(new BigDecimal(totalFormateado))
                .comision(new BigDecimal(comisionFormateado))
                .nombre_trabajador(servicioGenerales.getUsuarioModelTrabajador().getNombre())
                .promedio_estrellas(sumaCalificaciones/totalCalificaciones)
                .total_calificaciones(totalCalificaciones)
                .subservicios_lista(subServiciosLista)
                .status_historico(statusHistoricoLista)
                .build();

    }


}
