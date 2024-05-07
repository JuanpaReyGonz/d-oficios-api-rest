package com.doficios.apirest.Cotizacion;

import com.doficios.apirest.Trabajadores.TrabajadoresDisponiblesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionDTO {
    private int id_tiposervicio;
    private String tipo_servicio; // No se usa para confirmación de solicitud.
    private double comision;
    private double total;
    private String fecha_servicio;
    private String hora_servicio;
    private int id_direccion;

    List<SubserviciosCotizacionDTO> subservicioList;
    List<TrabajadoresDisponiblesDTO> trabajadorList;
}
