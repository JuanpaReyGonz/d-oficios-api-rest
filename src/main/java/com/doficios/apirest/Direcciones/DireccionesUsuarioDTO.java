package com.doficios.apirest.Direcciones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionesUsuarioDTO {
    private Integer id_direccion;
    private String entidad;
    private String municipio;
    private String localidad;
    private String domicilio;
    private String exterior;
    private String interior;
    private String colonia;
    private String cp;
    private boolean favorito;
}
