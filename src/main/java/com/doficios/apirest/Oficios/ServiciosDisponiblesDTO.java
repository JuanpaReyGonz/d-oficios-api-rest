package com.doficios.apirest.Oficios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiciosDisponiblesDTO {
    private Integer id_tiposervicio;
    private String descripcion;
    private Integer id_subservicio;
    private String descripcion_subservicios;
    private double minimo;
    private double maximo;
    private String unidad;
    private int duracion_min;
    private int iva;
}
