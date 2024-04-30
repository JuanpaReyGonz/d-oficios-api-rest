package com.doficios.apirest.Models;

import java.io.Serializable;
import java.util.Objects;

public class CalificacionesId implements Serializable {
    private Long id_usuario;
    private Long id_servicio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalificacionesId that = (CalificacionesId) o;
        return Objects.equals(id_usuario, that.id_usuario) && Objects.equals(id_servicio, that.id_servicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_usuario, id_servicio);
    }
}
