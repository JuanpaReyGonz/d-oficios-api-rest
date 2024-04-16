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
@Table(name = "subservicios")
public class SubServiciosModel {
    @ManyToOne
    @JoinColumn(name="id_tiposervicio")
    private TipoServicioModel tiposServicio;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subservicio",unique = true, nullable = false)
    private Integer idSubservicio;

    @Column(nullable = false, name = "descripcion")
    String descripcion_subservicios;
}
