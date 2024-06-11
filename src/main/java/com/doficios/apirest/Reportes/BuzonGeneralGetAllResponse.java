package com.doficios.apirest.Reportes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuzonGeneralGetAllResponse {
    private int id_reporte;
    private int id_usuario;
    private String correo;
    private String fecha;
    private String contenido;
}
