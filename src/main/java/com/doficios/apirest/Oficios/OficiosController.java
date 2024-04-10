package com.doficios.apirest.Oficios;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/oficios")
public class OficiosController {
    private static final Logger logger = LoggerFactory.getLogger(OficiosController.class);
   @Autowired
   OficiosService sOficios;
    @GetMapping("/listar")
    public List<ServiciosDisponiblesDTO> obtenerServiciosDisponibles() {
        logger.info("TOKEN VALIDO. Se está consumiendo el endpoint: http://localhost:8080/oficios/listar. Válido para cualquier usuario, no se revisa su rol.");
        return sOficios.obtenerServiciosConSubservicios();
    }

}
