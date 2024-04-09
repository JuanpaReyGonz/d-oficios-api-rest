package com.doficios.apirest.Oficios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "precios")
public class PreciosModel {
    @Id
    @ManyToOne
    @JoinColumn(name="id_tiposervicio")
    private TipoServicioModel tipoServicioModel;
    @Id
    @ManyToOne
    @JoinColumn(name="id_subservicio")
    private SubServiciosModel subServiciosModel;

    private double minimo;

    private double maximo;

    private String unidad;

    private int duracion_min;

    private int iva;

}
