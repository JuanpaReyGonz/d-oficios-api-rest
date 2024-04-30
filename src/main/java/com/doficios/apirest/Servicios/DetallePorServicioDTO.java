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
    //De aquí:
    private Long id_servicio;
    private String tipo_servicio;
    private int num_status;
    private String status;
    private String fecha_solicitud;
    private BigDecimal total;
    private BigDecimal comision;
    //Hasta aquí, se saca de TarjetasSolicitudesClienteDTO.
    //TarjetasSolicitudesClienteDTO tarjetasSolicitudesClienteDTO;
    private String nombre_trabajador; //Sale de serviciosModel.
    private double promedio_estrellas;
    private int total_calificaciones;
    List<SubServiciosListaDTO> subservicios_lista;
    List<StatusHistoricoDTO> status_historico;
}
