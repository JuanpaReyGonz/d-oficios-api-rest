package com.doficios.apirest.Trabajadores;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadoresDisponiblesDTO {
    private Long id_trabajador;
    private String nombre;
    private double calificacion;
    private Integer total_calificaciones;
    private Integer servicios_finalizados;
    private Integer prioridad = 0;
}
