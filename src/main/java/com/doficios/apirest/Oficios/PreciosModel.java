package com.doficios.apirest.Oficios;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "precios")
public class PreciosModel {
    @ManyToOne
    @JoinColumn(name="id_tiposervicio")
    private TipoServicioModel tipoServicioModel;

    @ManyToOne
    @JoinColumn(name="id_subservicio")
    private SubServiciosModel subServiciosModel;

    private double minimo;

    private double maximo;

    private String unidad;

    private int duracion_min;

    private int iva;

}
