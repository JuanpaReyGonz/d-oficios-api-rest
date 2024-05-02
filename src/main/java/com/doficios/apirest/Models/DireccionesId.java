package com.doficios.apirest.Models;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
@Data
public class DireccionesId implements Serializable {
    private Integer usuario;
    private Integer id_direccion;
    /*private MunicipiosId municipiosId;
    private LocalidadesId localidadesId;*/

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DireccionesId that = (DireccionesId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(id_direccion, that.id_direccion) && Objects.equals(municipiosId, that.municipiosId) && Objects.equals(localidadesId, that.localidadesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, id_direccion, municipiosId, localidadesId);
    }*/
}
