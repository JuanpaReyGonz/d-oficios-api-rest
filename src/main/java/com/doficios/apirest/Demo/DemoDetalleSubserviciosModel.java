package com.doficios.apirest.Demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoDetalleSubserviciosModel {
    private String subservicio;
    private double importe;
}
