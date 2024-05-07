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
@Table(name="localidades")
@IdClass(LocalidadesId.class)
public class LocalidadesModel {
    @Id
    @Column(name = "id_entidad")
    private Integer id_entidad;

    @Id
    @Column(name = "id_municipio")
    private Integer id_municipio;

    @Id
    @Column(name = "id_localidad")
    private Integer id_localidad;

    private String nombre;
}
