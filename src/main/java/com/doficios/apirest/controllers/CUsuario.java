package com.doficios.apirest.controllers;

import com.doficios.apirest.models.MUsuario;
import com.doficios.apirest.services.SUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class CUsuario {
    @Autowired
    SUsuario sUsuario;
    @PostMapping()
    public MUsuario registarUsuario(@RequestBody MUsuario usuario)
    {
        return sUsuario.registrarUsuario(usuario);
    }
}
