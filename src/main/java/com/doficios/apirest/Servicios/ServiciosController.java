package com.doficios.apirest.Servicios;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        logger.info("TOKEN VALIDO. Se valida usuario que hace la petición. Se está consumiendo el endpoint: http://localhost:8080/servicios/listado/cliente");
        List<TarjetasSolicitudesClienteDTO> tarjetasDTO = sServicios.obtenerTarjetasSolicitudesCliente();
        return ResponseEntity.ok(tarjetasDTO);
    }

}
