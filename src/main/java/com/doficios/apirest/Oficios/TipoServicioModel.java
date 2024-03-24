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
@Table(name="tipos_servicio")
public class TipoServicioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id_tiposervicio;

    @Column(nullable = false, unique = true)
    String descripcion;
}
