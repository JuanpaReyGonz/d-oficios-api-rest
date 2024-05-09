package com.doficios.apirest.Direcciones;

import com.doficios.apirest.Jwt.JwtService;
import com.doficios.apirest.Models.EntidadesModel;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direcciones")
public class DireccionesController {
    private static final Logger logger = LoggerFactory.getLogger(DireccionesController.class);
    @Autowired
    DireccionesService sDirecciones;

    @GetMapping()
    public List<DireccionesUsuarioDTO> obtenerDireccionesPorUsuario(HttpServletRequest requestIdUser){
        final String authHeader = requestIdUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        return ResponseEntity.ok(sDirecciones.obtenerDireccionesUsuario(username)).getBody();
    }

    @PostMapping("/createUpdate")
    public DireccionCreateUpdateResponse crearEditarDireccion(@RequestBody DireccionCreateUpdateRequest request, HttpServletRequest requestIdUser){
        final String authHeader = requestIdUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        logger.info("El usuario: "+username+" esta consumiendo /direcciones/createUpdate");
        if (request.getId_direccion()==0){
            logger.info("El usuario: "+username+" solicita grabar una nueva direccion");
        } else {
            logger.info("El usuario: "+username+" esta actualizando su id_direccion: "+request.getId_direccion());
        }
        return ResponseEntity.ok(sDirecciones.createUpdateDireccion(request, username)).getBody();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDireccionUsuario(@RequestParam("id_direccion") int id_direccion, HttpServletRequest requestIdUser){
        final String authHeader = requestIdUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        logger.info("El usuario: "+username+" esta consumiendo /direcciones/delete");
        boolean deleteDireccion = sDirecciones.borrarDireccionUsuario(id_direccion, username);
        if (deleteDireccion) {
            logger.info("Se elimino la direccion: "+id_direccion);
            return ResponseEntity.ok().build(); // Devolver estado 200 OK
        } else {
            logger.info("Ocurrio un error al intentar eliminar la direccion: "+id_direccion+" del usuario: "+username);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devolver un estado de error en caso de fallo
        }
    }

    @GetMapping("/entidades")
    public List<EntidadesModel> obtenerEntidades(){
        logger.info("Obteniendo informacion de entidades");
        return ResponseEntity.ok(sDirecciones.obtenerEntidades()).getBody();
    }

    @GetMapping("/municipios")
    public List<MunicipiosByEntidadResponse> obtenerMunicipios(@RequestParam("id_entidad") int entidad){
        logger.info("Obteniendo los municipios de la entidad: "+entidad);
        return  ResponseEntity.ok(sDirecciones.obtenerMunicipios(entidad)).getBody();
    }

    @GetMapping("/localidades")
    public List<LocalidadesByEntidadAndMunicipioResponse> obtenerLocalidades(@RequestParam("id_entidad") int entidad,
                                                                             @RequestParam("id_municipio") int municipio){
        logger.info("Obteniendo las localidades por entidad: "+entidad+" y municipio: "+municipio);
        return ResponseEntity.ok(sDirecciones.obtenerLocalidades(entidad,municipio)).getBody();
    }

}
