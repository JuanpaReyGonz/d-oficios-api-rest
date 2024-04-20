package com.doficios.apirest.Oficios;

import com.doficios.apirest.Models.PreciosModel;
import com.doficios.apirest.Models.SubServiciosModel;
import com.doficios.apirest.Models.TipoServicioModel;
import com.doficios.apirest.Repositories.PreciosRepository;
import com.doficios.apirest.Repositories.SubServiciosRepository;
import com.doficios.apirest.Repositories.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OficiosService {
    @Autowired
    TipoServicioRepository tipoServicioRepo;
    @Autowired
    PreciosRepository preciosRepo;
    @Autowired
    SubServiciosRepository subServiciosRepo;

    public List<ServiciosDisponiblesDTO> obtenerServiciosDisponibles() {
        List<TipoServicioModel> tiposServicio = tipoServicioRepo.findAll();
        List<ServiciosDisponiblesDTO> serviciosDisponiblesDTOList = new ArrayList<>();

        for (TipoServicioModel tipoServicio : tiposServicio) {

            List<SubServiciosModel> subServicios = tipoServicio.getSubservicios();
            List<SubServiciosDisponiblesDTO> subServiciosDisponiblesDTOList = new ArrayList<>();

            for (SubServiciosModel subServicio : subServicios) {
                //System.out.println("TipoServicio:"+tipoServicio.getId_tiposervicio()+" Subservicio:"+subServicio.getIdSubservicio());

                // Aquí obtenemos el precio para el sub-servicio actual y el tipo de servicio actual
                PreciosModel precio = preciosRepo.findByTipoServicioModelAndSubServiciosModel(
                        tipoServicio, subServicio
                );
                if (precio != null){
                    String descripcionSubServicio = subServiciosRepo.findDescripcionSubservicioByTipoAndId(tipoServicio.getId_tiposervicio(),subServicio.getIdSubservicio());
                    //System.out.println("Descripcion:" +descripcionSubServicio);

                    SubServiciosDisponiblesDTO subServicioDTO = SubServiciosDisponiblesDTO.builder()
                            .id_subservicio(subServicio.getIdSubservicio())
                            .descripcion_subservicios(descripcionSubServicio)
                            .minimo(precio.getMinimo())
                            .maximo(precio.getMaximo())
                            .unidad(precio.getUnidad())
                            .duracion_min(precio.getDuracion_min())
                            .iva(precio.getIva())
                            .build();
                    subServiciosDisponiblesDTOList.add(subServicioDTO);
                } else {
                    SubServiciosDisponiblesDTO subServicioDTO = SubServiciosDisponiblesDTO.builder()
                             .id_subservicio(subServicio.getIdSubservicio())
                             .descripcion_subservicios("Descripción no disponible")
                             .minimo(0.0)
                             .maximo(0.0)
                             .unidad("null")
                             .duracion_min(0)
                             .iva(0)
                             .build();
                     subServiciosDisponiblesDTOList.add(subServicioDTO);
                }
            }

            // Construir el DTO de servicios disponibles y agregarlo a la lista
            ServiciosDisponiblesDTO serviciosDisponiblesDTO = ServiciosDisponiblesDTO.builder()
                    .id_tiposervicio(tipoServicio.getId_tiposervicio())
                    .descripcion(tipoServicio.getDescripcion())
                    .subservicios(subServiciosDisponiblesDTOList)
                    .build();
            serviciosDisponiblesDTOList.add(serviciosDisponiblesDTO);
        }

        return serviciosDisponiblesDTOList;
    }
}