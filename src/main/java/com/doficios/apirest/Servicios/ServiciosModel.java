package com.doficios.apirest.Servicios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="servicios",uniqueConstraints = {@UniqueConstraint(columnNames = {"id_servicio"})})
public class ServiciosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Long id_servicio;

    Long cliente;

    Long status;

    LocalDateTime fecha_solicitud;

    LocalDateTime fecha_servicio;

    LocalDateTime hora_servicio;

    double importe;

    double comision;

    double importe_original;

    int tipo_servicio;

    int entidad;

    int municipio;

    int localidad;

    String domicilio;

}
