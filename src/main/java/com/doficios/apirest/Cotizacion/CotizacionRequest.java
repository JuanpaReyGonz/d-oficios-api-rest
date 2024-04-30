package com.doficios.apirest.Cotizacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionRequest {
    private int tipo_servicio;
    private String fecha_servicio;
    private String hora_servicio;
    private int id_direccion;
    private List<SubservicioListRequest> subservicioList;

}
