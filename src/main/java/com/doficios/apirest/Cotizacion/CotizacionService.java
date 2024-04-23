package com.doficios.apirest.Cotizacion;

import com.doficios.apirest.Models.PreciosModel;
import com.doficios.apirest.Models.TipoServicioModel;
import com.doficios.apirest.Models.TrabajadoresModel;
import com.doficios.apirest.Repositories.*;
import com.doficios.apirest.Trabajadores.TrabajadoresDisponiblesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
            trabajadoresDisponiblesDTO.setServicios_finalizados(serviciosRepo.countByUsuarioModelTrabajadorIdAndStatus(trabajador.getUsuarioModel().getId(),16));
            //trabajadoresDisponiblesDTO.setTotal_calificaciones(calificacionesRepo.countById_usuario(trabajador.getId_usuario()));
            trabajadoresDisponiblesDTO.setTotal_calificaciones(calificacionesRepo.countById_usuario(trabajador.getUsuarioModel().getId()));
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
}
