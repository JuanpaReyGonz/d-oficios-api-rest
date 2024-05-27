package com.doficios.apirest.Auth;

import com.doficios.apirest.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    //private final User user;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        /*if(authService.findByMail(request.getCorreo()).isEmpty())
        {
            return ResponseEntity.badRequest().body(Collections.singletonMap("ERROR","Email es incorrecto."));
        }
        return ResponseEntity.ok(authService.login(request));*/
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(Collections.singletonMap("ERROR","Credenciales inválidas"));
        }
    }

    @PostMapping(value = "register")
   // public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    public ResponseEntity<?> register(@RequestBody RegisterRequest request)
    {
        if (request.getCorreo().isEmpty() || request.getCorreo().isBlank() || authService.findByMail(request.getCorreo()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("ERROR","El email ya está registrado."));
        }
        return ResponseEntity.ok(authService.register(request));
    }
}
