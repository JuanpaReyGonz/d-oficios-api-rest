package com.doficios.apirest.Models;

import java.io.Serializable;
import java.util.Objects;

public class MunicipiosId implements Serializable {
    private Integer id_entidad;
    private Integer id_municipio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MunicipiosId that = (MunicipiosId) o;
        return Objects.equals(id_entidad, that.id_entidad) && Objects.equals(id_municipio, that.id_municipio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_entidad, id_municipio);
    }
}
