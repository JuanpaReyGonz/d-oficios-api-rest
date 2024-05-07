package com.doficios.apirest.Calificaciones;

import com.doficios.apirest.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calificacion")
public class CalificacionController {
    @Autowired
    CalificacionService sCalificacion;
    private static final Logger logger = LoggerFactory.getLogger(CalificacionController.class);

    @PostMapping("/cliente")
    public ResponseEntity<CalificacionResponse> guardaCalificacionCliente(@RequestBody CalificacionClienteRequest request, HttpServletRequest requestIdUser){
        final String authHeader = requestIdUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        logger.info("Se esta grabando calificacion por parte de cliente: "+username+" en servicio: "+request.getId_servicio());
        return ResponseEntity.ok(sCalificacion.guardarCalificacionCliente(request,username));
    }

    @PostMapping("/trabajador")
    public ResponseEntity<CalificacionResponse> guardaCalificacionTrabajador(@RequestBody CalificacionTrabajadorRequest request, HttpServletRequest requestIdUser){
        final String authHeader = requestIdUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        logger.info("Se esta grabando calificacion por parte de trabajador "+username+" en servicio: "+request.getId_servicio());
        return ResponseEntity.ok(sCalificacion.guardarCalificacionTrabajador(request,username));
    }

}
