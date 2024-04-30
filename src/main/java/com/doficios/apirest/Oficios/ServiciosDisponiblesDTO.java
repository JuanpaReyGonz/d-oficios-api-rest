package com.doficios.apirest.Oficios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiciosDisponiblesDTO {
    private Integer id_tiposervicio;
    private String descripcion;
    private List<SubServiciosDisponiblesDTO> subservicios;
}
