package com.doficios.apirest.Trabajadores;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/trabajadores")
public class TrabajadoresController {
    //private static final Logger logger = (Logger) LoggerFactory.getLogger(TrabajadoresController.class);
    @Autowired
    TrabajadoresService sTrabajadores;

    @GetMapping()
    public List<TrabajadoresDisponiblesDTO> obtenerTrabajadoresDisponibles(){
        //logger.info("Se esta extrayendo a los perfiles de trabajador más adecuados a la solicitud");
        return sTrabajadores.obtenerPerfilesTrabajador();
    }

}
