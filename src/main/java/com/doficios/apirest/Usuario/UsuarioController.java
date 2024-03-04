package com.doficios.apirest.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    UsuarioService sUsuario;
    @PostMapping()
    public UsuarioModel registrarUsuario(@RequestBody UsuarioModel usuario)
    {
        logger.info("Registrando Nuevo Usuario: "+usuario.getNombre());
        return sUsuario.registrarUsuario(usuario);
    }

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        logger.info("Obteniendo información de usuarios.");
        return ResponseEntity.ok(sUsuario.obtenerUsuarios()).getBody();//sUsuario.obtenerUsuarios();
    }

    @GetMapping("/tipoUsuario")
    public ArrayList<UsuarioModel> obtenerPorTipoUsuario(@RequestParam("tipoUsuario") char tipoUsuario){
        logger.info("Obteniendo todos los usuarios: "+tipoUsuario);
        return ResponseEntity.ok(sUsuario.obtenerPorTipoUsuario(tipoUsuario)).getBody();//this.sUsuario.obtenerPorTipoUsuario(tipoUsuario);
    }
}
