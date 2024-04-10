package com.doficios.apirest.Oficios;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@Embeddable
public class PreciosId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_tiposervicio",insertable = false, updatable = false)
    private TipoServicioModel tipoServicioModel;

    @ManyToOne
    @JoinColumn(name = "id_subservicio",insertable = false, updatable = false)
    private SubServiciosModel subServiciosModel;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreciosId preciosId = (PreciosId) o;
        return Objects.equals(tipoServicioModel, preciosId.tipoServicioModel) &&
                Objects.equals(subServiciosModel, preciosId.subServiciosModel);
    }
    @Override
    public int hashCode() {
        return Objects.hash(tipoServicioModel, subServiciosModel);
    }
}
