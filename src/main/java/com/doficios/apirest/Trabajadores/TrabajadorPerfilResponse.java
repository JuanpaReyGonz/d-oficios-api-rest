package com.doficios.apirest.Trabajadores;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorPerfilResponse {
    private int id_trabajador;
    private String nombre;
    private String correo;
    private String descripcion;
    private String experiencia;
    private String tipo_servicio;
    private double reputacion;
    private String fecha_registro;
    private int servicios_finalizados;
    private int total_calificaciones;
}
