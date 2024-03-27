package com.doficios.apirest.Demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DemoModelServicios {
    private int id_servicio;
    private String tipo_servicio;
    private String status;
    private String fecha_solicitud;
    private double importe;

}
