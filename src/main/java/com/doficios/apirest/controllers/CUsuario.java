package com.doficios.apirest.controllers;

import com.doficios.apirest.models.MUsuario;
import com.doficios.apirest.services.SUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class CUsuario {
    private static final Logger logger = LoggerFactory.getLogger(CUsuario.class);
    @Autowired
    SUsuario sUsuario;
    @PostMapping()
    public MUsuario registarUsuario(@RequestBody MUsuario usuario)
    {
        logger.info("Registrando Nuevo Usuario: "+usuario.getNombre());
        return sUsuario.registrarUsuario(usuario);
    }
}
