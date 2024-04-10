package com.doficios.apirest.Oficios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OficiosService {
    @Autowired
    TipoServicioRepository tipoServicioRepo;
    @Autowired
    PreciosRepository preciosRepo;

    public List<ServiciosDisponiblesDTO> obtenerServiciosConSubservicios() {
        List<TipoServicioModel> tipoServicios = tipoServicioRepo.findAll();
        return tipoServicios.stream()
                .map(this::mapToServiciosDisponiblesDTO)
                .collect(Collectors.toList());
    }

    private ServiciosDisponiblesDTO mapToServiciosDisponiblesDTO(TipoServicioModel tipoServicio) {
        ServiciosDisponiblesDTO dto = new ServiciosDisponiblesDTO();
        dto.setId_tiposervicio(tipoServicio.getId_tiposervicio());
        dto.setDescripcion(tipoServicio.getDescripcion());
        dto.setSubservicios(
                tipoServicio.getSubservicios().stream()
                        //.map(this::mapToSubServiciosDisponiblesDTO)
                        // Se cambia a expresión lambda, pues solo requiere del primer parámetro, no del segundo.
                        .map(subservicio -> mapToSubServiciosDisponiblesDTO(tipoServicio, subservicio))
                        .collect(Collectors.toList())
        );
        return dto;
    }
    private SubServiciosDisponiblesDTO mapToSubServiciosDisponiblesDTO(TipoServicioModel tipoServicio, SubServiciosModel subservicio) {
        SubServiciosDisponiblesDTO dto = new SubServiciosDisponiblesDTO();
        dto.setId_subservicio(subservicio.getId_subservicio());
        dto.setDescripcion_subservicios(subservicio.getDescripcion_subservicios());

        PreciosModel preciosModel = preciosRepo.findByTipoServicioModelAndSubServiciosModel(tipoServicio, subservicio);
        if (preciosModel != null) {
            double minimo = preciosModel.getMinimo();
            double maximo = preciosModel.getMaximo();
            String unidad = preciosModel.getUnidad();
            int duracion_min = preciosModel.getDuracion_min();
            int iva = preciosModel.getIva();
            dto.setMinimo(minimo);
            dto.setMaximo(maximo);
            dto.setUnidad(unidad);
            dto.setDuracion_min(duracion_min);
            dto.setIva(iva);
        }
        else {
            dto.setMinimo(0.0);
            dto.setMaximo(0.0);
            dto.setUnidad(null);
            dto.setDuracion_min(0);
            dto.setIva(0);
        }
        return dto;
    }
}
