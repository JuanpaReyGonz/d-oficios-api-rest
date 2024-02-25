package com.doficios.apirest.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepo;

    public UsuarioModel registrarUsuario(UsuarioModel usuario){
        return usuarioRepo.save(usuario);
    }

}
