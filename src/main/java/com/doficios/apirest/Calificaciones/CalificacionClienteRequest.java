package com.doficios.apirest.Calificaciones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalificacionClienteRequest {
    //public Long getId_servicio;
    private Integer id_servicio;
    private int calificacion_servicio;
    private int calificacion_usuario;
    private String comentario_servicio;
    private String comentario_usuario;
}
