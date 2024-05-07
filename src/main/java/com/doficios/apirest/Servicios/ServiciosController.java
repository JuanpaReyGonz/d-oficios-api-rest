package com.doficios.apirest.Servicios;

import com.doficios.apirest.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServiciosController {
    private static final Logger logger = LoggerFactory.getLogger(ServiciosController.class);
    //Nomenclatura @Autowired es para inyección de dependencias. La inyección de dependencias es una técnica fundamental en Spring que promueve la inversión de control (IoC) y facilita la escritura de código más modular, flexible y fácil de probar.

    @Autowired
    ServiciosService sServicios;

    @GetMapping("/listado/cliente")
    public ResponseEntity<List<TarjetasSolicitudesClienteDTO>> obtenerTarjetasSolicitudesCliente(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        logger.info("TOKEN VALIDO. El usuario: "+username+" está consumiendo el endpoint: /servicios/listado/cliente");
        List<TarjetasSolicitudesClienteDTO> tarjetasDTO = sServicios.obtenerTarjetasSolicitudesCliente(username);
        return ResponseEntity.ok(tarjetasDTO);
    }

    @PostMapping()
    public ResponseEntity<DetallePorServicioDTO> obtenerDetalle(@RequestBody DetallePorServicioRequest request,HttpServletRequest user){
        final String authHeader = user.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        logger.info("Se está consumiendo el endpoint /servicios");
        logger.info("Obteniendo las tareas del servicio: "+request.getId_servicio());
        return ResponseEntity.ok(sServicios.obtenerDetalleServicio(request.getId_servicio(),username));
    }

}
