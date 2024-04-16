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

    @GetMapping(value = "dummy_detalle")
    //public List<DemoDetalleServiciosModel> obtenerDetalleServicio(){
    public DemoDetalleServiciosModel obtenerDetalleServicio(){
        logger.info("TOKEN VALIDO. Se está consumiendo el endpoint: http://localhost:8080/api/v1/dummy_detalle");

        //Declaración de lista secundaria para detalle de subservicios e importes por subservicios.
        List<DemoDetalleSubserviciosModel> demoDetalle = new ArrayList<>();
        demoDetalle.add(new DemoDetalleSubserviciosModel("Instalación de sanitario", 550.50));
        demoDetalle.add(new DemoDetalleSubserviciosModel("Instalación de llave para lavabo", 320.60));
        //Declaración de lista secundaria para histórico de estatus.
        List<DemoDetalleEstadosModel> demoEstadosList = new ArrayList<>();
        demoEstadosList.add(new DemoDetalleEstadosModel(1,"Asignando trabajador a tu solicitud de servicio","23-03-2024 15:30"));
        demoEstadosList.add(new DemoDetalleEstadosModel(2,"Solicitud confirmada por trabajador","24-03-2024 10:00"));
        demoEstadosList.add(new DemoDetalleEstadosModel(3,"Validando datos con el cliente","25-03-2024 08:00"));
        demoEstadosList.add(new DemoDetalleEstadosModel(4,"En espera de pago","26-03-2024 16:30"));
        demoEstadosList.add(new DemoDetalleEstadosModel(5,"Validando pago","27-03-2024 15:30"));
        demoEstadosList.add(new DemoDetalleEstadosModel(6,"Pago confirmado","27-03-2024 18:00"));
        demoEstadosList.add(new DemoDetalleEstadosModel(7,"Servicio agendado","27-03-2024 18:01"));
        demoEstadosList.add(new DemoDetalleEstadosModel(8,"Trabajador en camino","29-03-2024 16:30"));
        demoEstadosList.add(new DemoDetalleEstadosModel(9,"Servicio iniciado. En espera de confirmación","29-03-2024 16:45"));
        demoEstadosList.add(new DemoDetalleEstadosModel(10,"Servicio en progreso","29-03-2024 16:50"));
        demoEstadosList.add(new DemoDetalleEstadosModel(11,"Servicio finalizado por trabajador","29-03-2024 17:30"));
        demoEstadosList.add(new DemoDetalleEstadosModel(12,"En revisión por el cliente","29-03-2024 17:31"));
        demoEstadosList.add(new DemoDetalleEstadosModel(13,"Aprobado por el cliente","30-03-2024 15:30"));
        demoEstadosList.add(new DemoDetalleEstadosModel(14,"Pago en proceso","30-03-2024 15:32"));
        demoEstadosList.add(new DemoDetalleEstadosModel(15,"Servicio pagado","31-03-2024 08:00"));
        demoEstadosList.add(new DemoDetalleEstadosModel(16,"Servicio finalizado","31-03-2024 08:30"));

        /*//Lista del JSON GENERAL QUE SE MANDA
        List<DemoDetalleServiciosModel> modelServicios = new ArrayList<>();
        //Declarando detalle de servicio con Listas
        modelServicios.add(
                new DemoDetalleServiciosModel(
                        1002,
                        "Fontanería",
                        5,
                        "Validando pago",
                        "27-03-2024 10:00",
                        871.10,
                        "Reyes|González|Juan Pablo",
                        4.5,
                        43,
                        demoDetalle,
                        demoEstadosList
                ));*/
        DemoDetalleServiciosModel modelServicios =
                new DemoDetalleServiciosModel(
                        1002,
                        "Fontanería",
                        12,
                        "Validando pago",
                        "27-03-2024 10:00",
                        871.10,
                        "Reyes|González|Juan Pablo",
                        4.5,
                        43,
                        demoDetalle,
                        demoEstadosList
                );
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