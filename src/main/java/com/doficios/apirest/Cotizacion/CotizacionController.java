package com.doficios.apirest.Cotizacion;

import com.doficios.apirest.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
    //Método para devolver posibles trabajadores al cliente.
    @PostMapping()
    public ResponseEntity<CotizacionDTO> obtenerCotizacion(@RequestBody CotizacionRequest request){
        logger.info("Se esta consumiendo el endpoint /cotizacion");
        //System.out.println(sCotizacion.obtenerCotizacion(request).toString());
        return ResponseEntity.ok(sCotizacion.obtenerCotizacion(request));
    }
    //Método para grabar un nuevo servicio
    @PostMapping("/confirmacion")
    public ResponseEntity<ConfirmarCotizacionResponse> confirmarCotizacion(@RequestBody CotizacionDTO request, HttpServletRequest requestIdUser){
        final String authHeader = requestIdUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);

        logger.info("Se esta consumiendo /cotizacion/confirmacion. Grabando servicio del usuario "+username+" en tablas.");
        return ResponseEntity.ok(sCotizacion.confirmarCotizacion(request,username));
    }


}
