package com.doficios.apirest.Trabajadores;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorUpdatePerfilDTO {
    private String descripcion;
    private String experiencia;
    private int id_tipo_servicio;

}
