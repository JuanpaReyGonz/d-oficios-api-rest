package com.doficios.apirest.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="servicios",uniqueConstraints = {@UniqueConstraint(columnNames = {"id_servicio"})})
public class ServiciosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Long id_servicio;
    //name es de la tabla Servicios y id_usuario de la tabla Usuarios
    //JsonIgnoreProperties para evitar que se manden campos que no requerimos mapear y puedan ser sensibles o truenen la aplicación.
    @ManyToOne
    @JoinColumn(name="cliente", referencedColumnName = "id_usuario")
    @JsonIgnoreProperties({"id","correo","telefono","password","tipoUsuario","fotoPerfil"})
    private UsuarioModel usuarioModel;

    @ManyToOne
    @JoinColumn(name="trabajador", referencedColumnName = "id_usuario")
    @JsonIgnoreProperties({"id","correo","telefono","password","tipoUsuario","fotoPerfil"})
    private UsuarioModel usuarioModelTrabajador;

    @ManyToOne
    @JoinColumn(name="status")
    @JsonIgnoreProperties({"status"})
    private StatusDeServicioModel statusModel;
    //Queda mejor mapear las fechas como String que como LocalDateTime, porque sino devuelve una T entre fecha y hora.
    String fecha_solicitud;
    //LocalDateTime fecha_servicio;
    String fecha_servicio;
    //LocalDateTime hora_servicio;
    String hora_servicio;
    double importe;
    Double comision;
    //double es un valor primitivo de Java y no se puede devolver un null si en la BD está mapeado así. Double si lo hace.
    Double importe_original;

    //tipo_servicio es columna de la tabla Servicios y id_tiposervicio de la tabla tipos_servicio
    @ManyToOne
    @JoinColumn(name="tipo_servicio", referencedColumnName = "id_tiposervicio")
    @JsonIgnoreProperties({"id_tiposervicio","subservicios"})
    private TipoServicioModel tipoServicioModel;
    int entidad;
    int municipio;
    int localidad;
    String domicilio;
    String exterior;
    String interior;
    String colonia;
    String cp;
}
