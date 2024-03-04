package com.doficios.apirest.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepo;

    public UsuarioModel registrarUsuario(UsuarioModel usuario){
        return usuarioRepo.save(usuario);
    }

    @Transactional(readOnly = true)
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepo.findAll();
    }

    @Transactional(readOnly = true)
    public ArrayList<UsuarioModel> obtenerPorTipoUsuario(char tipoUsuario){
        return usuarioRepo.findBytipoUsuario(tipoUsuario);
    }


}
