package com.doficios.apirest.Servicios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tareas_servicio")
@IdClass(TareasPorServicioId.class)
public class TareasPorServicioModel implements Serializable {
    @Id
    @Column(name = "id_tarea", nullable = false)
    Integer idTarea;
    @Id
    @Column(name="id_servicio",unique = true, nullable = false)
    Integer idServicio;

    double importe;
    double iva;

    @ManyToOne
    @JoinColumn(name="status")
    @JsonIgnoreProperties({"status"})
    private StatusDeTareaModel statusTarea;
    //Integer status;

    Integer sub_servicio;
    Integer unidad;

}
