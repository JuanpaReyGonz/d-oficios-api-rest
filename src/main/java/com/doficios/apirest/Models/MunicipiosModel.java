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
@Table(name="municipios")
@IdClass(MunicipiosId.class)
public class MunicipiosModel {
    @Id
    @Column(name = "id_entidad")
    private Integer id_entidad;

    @Id
    @Column(name = "id_municipio")
    private Integer id_municipio;
    private String nombre;

}
