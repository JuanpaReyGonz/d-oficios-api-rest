package com.doficios.apirest.Servicios;

import com.doficios.apirest.Models.CalificacionesId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name="historial_status")
@IdClass(HistorialStatusId.class)
public class HistorialStatusModel {
    @Id
    @Column(name = "id_servicio", nullable = false)
    Integer idServicio;

    @Id
    @Column(name = "id_usuario", nullable = false)
    Integer idUsuario;


    @ManyToOne
    @JoinColumn(name="status")
    @JsonIgnoreProperties({"status"})
    private StatusDeServicioModel statusModel;
    //Integer Status;

    String fecha;
}
