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
@Table(name = "calificaciones")
@IdClass(CalificacionesId.class)
public class CalificacionesModel {
    @Id
    @Column(name = "id_usuario")
    private Long id_usuario;

    @Id
    @Column(name = "id_servicio")
    private Long id_servicio;

    private Integer calificacion;

    private String comentario;

}
