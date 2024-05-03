package com.doficios.apirest.Servicios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistoricoDTO {
    private int num_status;
    private String status;
    private String fecha;
}
