package com.doficios.apirest.Trabajadores;

import com.doficios.apirest.Jwt.JwtService;
import com.doficios.apirest.Models.UsuarioModel;
import com.doficios.apirest.Repositories.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajadores")
public class TrabajadoresController {
    @Autowired
    UsuarioRepository usuarioRepo;
    private static final Logger logger = LoggerFactory.getLogger(TrabajadoresController.class);
    @Autowired
    TrabajadoresService sTrabajadores;

    @GetMapping()
    public List<TrabajadoresDisponiblesDTO> obtenerTrabajadoresDisponibles(){
        //logger.info("Se esta extrayendo a los perfiles de trabajador más adecuados a la solicitud");
        return sTrabajadores.obtenerPerfilesTrabajador();
    }

    @PostMapping("/perfil")
    public ResponseEntity<TrabajadorPerfilResponse> mostrarPerfilTrabajador(@RequestBody TrabajadorPerfilRequest request, HttpServletRequest requestUser){
        final String authHeader = requestUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        if (request.getId_trabajador()==0){
            int usuario = usuarioRepo.findByCorreo(username);
            logger.info("El trabajador: "+username+" esta consumiendo /trabajadores/perfil");
            return ResponseEntity.ok(sTrabajadores.mostrarPerfilTrabajador(usuario));
        }else{
            logger.info("El cliente esta revisando el perfil del trabajador: "+username+". Consumiendo /trabajadores/perfil");
            return ResponseEntity.ok(sTrabajadores.mostrarPerfilTrabajador(request.getId_trabajador()));
        }
    }

    @PostMapping("/updatePerfil")
    public ResponseEntity<TrabajadorPerfilRequest> updatePerfilTrabajador(@RequestBody TrabajadorUpdatePerfilDTO request, HttpServletRequest requestUser){
        final String authHeader = requestUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        int usuario = usuarioRepo.findByCorreo(username);
        logger.info("El trabajador: "+username+" esta consumiendo /trabajadores/updatePerfil");
        logger.info("Actualizando la información del perfil de este trabajador");
        return  ResponseEntity.ok(sTrabajadores.updatePerfilTrabajador(request, usuario));
    }

}
