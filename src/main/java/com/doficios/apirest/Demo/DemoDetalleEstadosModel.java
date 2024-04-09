package com.doficios.apirest.Demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoDetalleEstadosModel {
    private int num_status;
    private String status;
    private String fecha;
}
