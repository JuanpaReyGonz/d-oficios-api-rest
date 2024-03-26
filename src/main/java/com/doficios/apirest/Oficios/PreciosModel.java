package com.doficios.apirest.Oficios;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Table(name = "precios")
public class PreciosModel {
    @Id
    @JoinColumn(name="id_tipoServicio")
    private TipoServicioModel tipoServicioModel;


}
