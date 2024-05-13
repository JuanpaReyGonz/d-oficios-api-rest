package com.doficios.apirest.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name = "candidatos_servicio")
@IdClass(CandidatoServicioId.class)
public class CandidatoServicioModel {
    @Id
    private Long id_servicio;
    @Id
    private Long id_trabajador;
    private char status;
    private String motivo;
    private int prioridad;
    private int intento;
}
