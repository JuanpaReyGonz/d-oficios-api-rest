package com.doficios.apirest.Demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoDetalleServiciosModel {
    private int id_servicio;
    private String tipo_servicio;
    private int num_status;
    private String status;
    private String fecha_solicitud;
    private double importe;
    private String nombre_trabajador;
    private double promedio_estrellas;
    private int total_calificaciones;
    List<DemoDetalleSubserviciosModel> subservicios_lista;

    List<DemoDetalleEstadosModel> status_historico;

}

/*package com.doficios.apirest.Demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoDetalleServiciosModel {
    private int id_servicio;
    private String tipo_servicio;
    private int num_status;
    private String status;
    private String fecha_solicitud;
    private double importe;
    private String nombre_trabajador;
    private double promedio_estrellas;
    private double total_calificaciones;
    List<DemoDetalleSubserviciosModel> subservicios_lista;

    List<DemoDetalleEstadosModel> estados_historico;

}*/

