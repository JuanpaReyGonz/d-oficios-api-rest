package com.doficios.apirest.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    UsuarioService sUsuario;
    @PostMapping()
    public UsuarioModel registarUsuario(@RequestBody UsuarioModel usuario)
    {
        logger.info("Registrando Nuevo Usuario: "+usuario.getNombre());
        return sUsuario.registrarUsuario(usuario);
    }
}
