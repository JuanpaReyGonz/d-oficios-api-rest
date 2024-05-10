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
@Table(name="buzon_general")
public class BuzonReportesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_reporte;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonIgnoreProperties({"correo","telefono","password","tipoUsuario","fotoPerfil"})
    private UsuarioModel usuarioModel;

    LocalDateTime fecha;
    String contenido;
}
