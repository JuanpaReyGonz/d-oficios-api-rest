package com.doficios.apirest.Models;

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
public class PreciosModel implements java.io.Serializable{
    //Se implementa de Serializable para el EmbeddedId realizado en la clase PreciosId. Esto debido a que la tabla Precios solo tiene 2FK que son PK.
    @EmbeddedId
    private PreciosId id;
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
