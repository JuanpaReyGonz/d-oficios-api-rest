package com.doficios.apirest.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class LocalidadesId implements Serializable {
    private Integer id_entidad;
    private Integer id_municipio;
    private Integer id_localidad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalidadesId that = (LocalidadesId) o;
        return Objects.equals(id_entidad, that.id_entidad) && Objects.equals(id_municipio, that.id_municipio) && Objects.equals(id_localidad, that.id_localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_entidad, id_municipio, id_localidad);
    }
}
