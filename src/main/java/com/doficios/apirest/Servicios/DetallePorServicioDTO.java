package com.doficios.apirest.Servicios;

import com.doficios.apirest.Demo.DemoDetalleEstadosModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetallePorServicioDTO {
    private Long id_servicio;
    private String tipo_servicio;
    private int num_status;
    private String status;
    private String fecha_solicitud;
    private BigDecimal total;
    private BigDecimal comision;
    private String nombre_trabajador; //Sale de serviciosModel.
    private double promedio_estrellas;
    private int total_calificaciones;
    private String fecha_servicio;
    private String hora_servicio;
    private String lugar_servicio;
    //Datos para detalle desde usuario cliente
    private long id_trabajador;
    private String telefono_trabajador;
    private int calificacion_servicio;
    private int calificacion_trabajador;
    private String comentario_servicio;
    private String comentario_trabajador;
    private int servicios_finalizados_trabajador;
    //Datos para detalle desde trabajador
    private String nombre_cliente;
    private double promedio_estrellas_cliente;
    private int total_calificaciones_cliente;
    private int servicios_finalizados_cliente;
    private String telefono_cliente;
    private int calificacion_cliente;
    private String comentario_cliente;

    List<SubServiciosListaDTO> subservicios_lista;
    List<StatusHistoricoDTO> status_historico;
}
