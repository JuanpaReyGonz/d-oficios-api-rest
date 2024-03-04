package com.doficios.apirest.Oficios;

import jakarta.persistence.*;

@Entity
@Table(name="tipos_servicio")
public class OficiosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id_tiposervicio;
}
