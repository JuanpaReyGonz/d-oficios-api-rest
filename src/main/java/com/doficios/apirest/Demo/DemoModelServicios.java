package com.doficios.apirest.Demo;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class DemoModelServicios {
    private int id_servicio;
    private String tipo_servicio;
    private String status;
    private String fecha_solicitud;
    private double importe;

}
