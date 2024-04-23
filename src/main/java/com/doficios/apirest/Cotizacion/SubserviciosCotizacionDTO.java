package com.doficios.apirest.Cotizacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubserviciosCotizacionDTO {
    private int id_subservicio;
    private String subservicio;
    private int cantidad;
    private double precio_unitario;
    private double iva;
    private double subtotal;

}
