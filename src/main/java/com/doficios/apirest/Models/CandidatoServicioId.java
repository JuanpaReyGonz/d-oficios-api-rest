package com.doficios.apirest.Models;

import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class CandidatoServicioId implements Serializable {
    private Long id_servicio;
    private Long id_trabajador;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatoServicioId that = (CandidatoServicioId) o;
        return Objects.equals(id_servicio, that.id_servicio) && Objects.equals(id_trabajador, that.id_trabajador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_servicio, id_trabajador);
    }
}
