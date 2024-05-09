package com.doficios.apirest.Direcciones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalidadesByEntidadAndMunicipioResponse {
    private int id_localidad;
    private String nombre;
}
