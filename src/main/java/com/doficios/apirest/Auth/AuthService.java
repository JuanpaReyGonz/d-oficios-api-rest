package com.doficios.apirest.Auth;

import com.doficios.apirest.Jwt.JwtService;
import com.doficios.apirest.User.Role;
import com.doficios.apirest.User.User;
import com.doficios.apirest.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
        //UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        UserDetails user=userRepository.findByCorreo(request.getCorreo()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                //.username(request.getUsername())
                .correo(request.getCorreo())
                .password(passwordEncoder.encode( request.getPassword()))
                //.firstname(request.getFirstname())
                .nombre(request.getNombre())
                //.lastname(request.lastname)
                .telefono(request.getTelefono())
                //.country(request.getCountry())
                //.role(Role.C) //Revisar y cambiar por libre.
                .tipoUsuario(request.getTipoUsuario())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

    public Optional<User> findByMail(String correo){
        return userRepository.findByCorreo(correo);
    }

}