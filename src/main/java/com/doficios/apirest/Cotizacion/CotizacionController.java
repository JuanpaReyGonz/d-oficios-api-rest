package com.doficios.apirest.Cotizacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cotizacion")
public class CotizacionController {
    private static final Logger logger = LoggerFactory.getLogger(CotizacionController.class);
    @Autowired
    CotizacionService sCotizacion;
    @PostMapping()
    public ResponseEntity<CotizacionDTO> obtenerCotizacion(@RequestBody CotizacionRequest request){
        logger.info("Se esta consumiendo el endpoint /cotizacion");
        System.out.println(sCotizacion.obtenerCotizacion(request).toString());
        return ResponseEntity.ok(sCotizacion.obtenerCotizacion(request));
    }


}
