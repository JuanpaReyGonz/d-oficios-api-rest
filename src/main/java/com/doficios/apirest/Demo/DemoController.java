package com.doficios.apirest.Demo;

import com.doficios.apirest.Jwt.JwtAuthenticationFilter;
import com.doficios.apirest.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    /*@GetMapping(value = "dummy")
    public DemoModelServicios obtenerServicios(){
        DemoModelServicios modelServicios = new DemoModelServicios();
        modelServicios.setId_servicio(1002);
        modelServicios.setTipo_servicio("Jardinería");
        modelServicios.setStatus("Validando pago");
        modelServicios.setFecha_solicitud("27-03-2024 10:00");
        modelServicios.setImporte(734.50);
        return modelServicios;
    }*/
    @GetMapping(value = "dummy")
    public List<DemoModelServicios> obtenerServicios(){
        logger.info("TOKEN VALIDO. Se está consumiendo el endpoint: http://localhost:8080/api/v1/dummy");
        List<DemoModelServicios> modelServicios = new ArrayList<>();
       //Declarando cada servicio
        modelServicios.add(
                new DemoModelServicios(
                        1002,
                        "Jardinería",
                        "Validando pago",
                        "27-03-2024 10:00",
                        734.50));
        modelServicios.add(
                new DemoModelServicios(
                        4114,
                        "Fontanería",
                        "Trabajador en Camino",
                        "28-03-2024 14:00",
                        650.00));

        return modelServicios;
    }

    @PostMapping(value = "demo")
    public String welcome(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        jwtService.getUsernameFromToken(token);

        return jwtService.getUsernameFromToken(token);
    }
}
