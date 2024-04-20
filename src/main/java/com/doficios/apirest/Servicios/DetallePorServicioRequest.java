package com.doficios.apirest.Servicios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePorServicioRequest {
    Integer id_servicio;
}
