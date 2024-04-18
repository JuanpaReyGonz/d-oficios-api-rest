package com.doficios.apirest.Servicios;

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




        return DetallePorServicioDTO.builder()
                .id_servicio(servicioGenerales.getId_servicio())
                .tipo_servicio(servicioGenerales.getTipoServicioModel().getDescripcion())
                .num_status(servicioGenerales.getStatusModel().getStatus())
                .status(servicioGenerales.getStatusModel().getDescripcion())
                .fecha_solicitud(servicioGenerales.getFecha_solicitud())
                .importe(servicioGenerales.getImporte())
                .nombre_trabajador(servicioGenerales.getUsuarioModelTrabajador().getNombre())
                .promedio_estrellas(0.0)
                .total_calificaciones(0)
                .build();

    }


}
