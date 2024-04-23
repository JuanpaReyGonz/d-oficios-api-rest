package com.doficios.apirest.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@IdClass(TrabajadoresId.class)
@Table(name="trabajadores")
public class TrabajadoresModel{
    @Id
    @Column(name = "id_usuario")
    private Integer id_usuario;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @JsonIgnoreProperties({"correo","telefono","password","tipoUsuario","fotoPerfil"})
    private UsuarioModel usuarioModel;
    //private Integer id_usuario;
    private String descripcion;
    private String experiencia;
    //@name es de mi tabla actual, el FK de id_tiposervicio de la tabla de tipos_servicios
    @ManyToOne
    @JoinColumn(name="tipo_servicio", referencedColumnName = "id_tiposervicio")
    //@JsonIgnoreProperties({"subservicios"})
    private TipoServicioModel tipoServicioModel;

    private double reputacion;
    private String insignia;
    private boolean recomendado;
    private String fecha_registro;
    private String clabe;
}
