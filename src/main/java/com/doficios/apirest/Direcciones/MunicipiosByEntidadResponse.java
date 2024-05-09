package com.doficios.apirest.Direcciones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MunicipiosByEntidadResponse {
    private int id_municipio;
    private String nombre;
}
