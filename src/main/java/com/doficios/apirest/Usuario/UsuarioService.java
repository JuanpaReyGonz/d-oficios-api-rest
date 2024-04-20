package com.doficios.apirest.Usuario;

import com.doficios.apirest.Models.UsuarioModel;
import com.doficios.apirest.Repositories.UsuarioRepository;
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

    //La nomenclatura @Transactional(readOnly = true) indica que el método será ejecutado en un contexto transaccional, pero se configura como solo lectura.
    @Transactional(readOnly = true)
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepo.findAll();
    }

    @Transactional(readOnly = true)
    public ArrayList<UsuarioModel> obtenerPorTipoUsuario(char tipoUsuario){
        return usuarioRepo.findBytipoUsuario(tipoUsuario);
    }


}
