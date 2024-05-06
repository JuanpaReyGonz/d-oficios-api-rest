package com.doficios.apirest.Status;

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
@RequestMapping("/updateStatus")
public class UpdateStatusController {
    private static final Logger logger = LoggerFactory.getLogger(UpdateStatusController.class);
    @Autowired
    UpdateStatusService sUpdateStatus;
    @PostMapping()
    public ResponseEntity<UpdateStatusResponse> actualizaStatus(@RequestBody UpdateStatusRequest request, HttpServletRequest requestIdUser){
        final String authHeader = requestIdUser.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        JwtService jwtService = new JwtService();
        String username = jwtService.getUsernameFromToken(token);
        logger.info("Se esta consumiendo /updateStatus. Grabando para servicio "+request.getId_servicio()+". En status: "+request.getStatus()+" en tablas.");
        return ResponseEntity.ok(sUpdateStatus.actualizarStatus(request,username));
    }
}
