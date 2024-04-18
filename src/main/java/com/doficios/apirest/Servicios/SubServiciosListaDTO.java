package com.doficios.apirest.Servicios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubServiciosListaDTO {
    private String subservicio;
    private double precio_unitario;
    private int cantidad;
    private double iva;
    private double subtotal;
}
