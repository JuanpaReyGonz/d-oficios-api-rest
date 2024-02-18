package com.doficios.apirest.services;

import com.doficios.apirest.models.MUsuario;
import com.doficios.apirest.repositories.RUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SUsuario {
    @Autowired
    RUsuario usuarioRepo;

    public MUsuario registrarUsuario(MUsuario usuario){
        return usuarioRepo.save(usuario);
    }

}
