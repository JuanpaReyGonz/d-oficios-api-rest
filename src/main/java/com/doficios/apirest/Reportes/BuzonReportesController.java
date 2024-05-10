package com.doficios.apirest.Reportes;

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
@RequestMapping("/reportes")
public class BuzonReportesController {
    private static final Logger logger = LoggerFactory.getLogger(BuzonReportesController.class);
    @Autowired
    BuzonReporteService sbuzonReporte;

    @PostMapping("/insert")
    public ResponseEntity<BuzonGeneralResponse> insertarReporteBuzonGeneral(@RequestBody BuzonGeneralRequest request, HttpServletRequest requestIdUser){
        final String authHeader = requestIdUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        logger.info("Se esta grabando un nuevo registro en buzon general por el usuario: "+username);
        return ResponseEntity.ok(sbuzonReporte.insertBuzonGeneral(request,username));
    }
}
