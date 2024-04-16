package com.doficios.apirest.Servicios;

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
//@UniqueConstraints se utiliza para garantizar que los valores en una o más columnas de una tabla de base de datos asociada a una entidad sean únicos, lo que puede ser útil para mantener la integridad de los datos y evitar la duplicación no deseada.
@Table(name="status_tarea_servicio",uniqueConstraints = {@UniqueConstraint(columnNames = {"status"})})
public class StatusDeTareaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer status;

    String descripcion;
}
