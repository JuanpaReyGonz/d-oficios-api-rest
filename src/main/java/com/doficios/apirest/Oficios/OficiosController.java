package com.doficios.apirest.Oficios;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/oficios")
@RequiredArgsConstructor
public class OficiosController {
    private TipoServicioRepository tipoServicioRepository;
    private SubServiciosRepository subServiciosRepository;

    /*@GetMapping
    public List<?> obtenerOficios(TipoServicioModel tipoServicioModel, SubServiciosModel subServiciosModel){
        List<Categoria> listaOficios = tipoServicioRepository.findAll();
        subServiciosModel.getDescripcion();
    }*/
    /*public ArrayList<?> obtenerOficios(){
        return  ResponseEntity.ok(tipoServicioRepository.findAll());
    }*/
}
