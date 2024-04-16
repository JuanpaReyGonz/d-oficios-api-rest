package com.doficios.apirest.Servicios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="historial_status")
public class HistorialStatusModel {
    @Id
    @Column(name = "id_servicio", nullable = false)
    Integer idServicio;

    @Id
    @Column(name = "id_usuario", nullable = false)
    Integer idUsuario;

    @Id
    @Column(name="status", nullable = false)
    Integer Status;

    String fecha;
}
