package com.doficios.apirest.Servicios;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class TareasPorServicioId implements Serializable {
    private Integer idTarea;
    private Integer idServicio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareasPorServicioId that = (TareasPorServicioId) o;
        return Objects.equals(idTarea, that.idTarea) && Objects.equals(idServicio, that.idServicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarea, idServicio);
    }
}
