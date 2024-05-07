package com.doficios.apirest.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="direcciones")
public class DireccionesModel {
    @EmbeddedId
    private DireccionesId id;
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario",insertable=false, updatable=false)
    @JsonIgnoreProperties({"correo","telefono","password","tipoUsuario","fotoPerfil"})
    private UsuarioModel usuarioModel;
    //@Column(name = "usuario",insertable=false, updatable=false)
    //private Integer usuario;

    @Column(name = "id_direccion",insertable=false, updatable=false)
    private Integer id_direccion;

    /*@ManyToOne
    @JoinColumn(name="entidad", referencedColumnName = "id_entidad")
    private EntidadesModel entidadModel;

    @ManyToOne
    @MapsId("municipiosId")
    private MunicipiosModel municipioModel;

    @ManyToOne
    @MapsId("localidadesId")
    private LocalidadesModel localidadModel;*/
    private int entidad;
    private int municipio;
    private int localidad;

    private String domicilio;
    private String exterior;
    private String interior;
    private String colonia;
    private String cp;
    private byte favorito;
}
