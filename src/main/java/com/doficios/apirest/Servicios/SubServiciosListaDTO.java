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
public class SubServiciosListaDTO {
    private String subservicio;
    private BigDecimal precio_unitario;
    private int cantidad;
    private BigDecimal iva;
    private BigDecimal subtotal;
}
