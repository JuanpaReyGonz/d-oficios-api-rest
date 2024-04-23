package com.doficios.apirest.Cotizacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubservicioListRequest {
    private int id_subservicio;
    private int cantidad;
}
