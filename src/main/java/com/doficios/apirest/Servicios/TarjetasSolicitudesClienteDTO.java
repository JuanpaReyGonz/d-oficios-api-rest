package com.doficios.apirest.Servicios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarjetasSolicitudesClienteDTO {
    private Long id_servicio;
    private String tipo_servicio;
    private int id_status;
    private String status;
    private String fecha_solicitud;
    private BigDecimal importe;
}
