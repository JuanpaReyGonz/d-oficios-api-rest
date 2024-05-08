package com.doficios.apirest.Trabajadores;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorPerfilRequest {
    private int id_trabajador;
}
