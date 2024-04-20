package com.doficios.apirest.Models;

import java.io.Serializable;
import java.util.Objects;

public class HistorialStatusId implements Serializable {
    private Integer idServicio;
    private Integer idUsuario;
    private StatusDeServicioModel statusModel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialStatusId that = (HistorialStatusId) o;
        return Objects.equals(idServicio, that.idServicio) && Objects.equals(idUsuario, that.idUsuario) && Objects.equals(statusModel, that.statusModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idServicio, idUsuario, statusModel);
    }
}
