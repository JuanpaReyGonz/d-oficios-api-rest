package com.doficios.apirest.Oficios;

import lombok.RequiredArgsConstructor;
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
   @Autowired
   OficiosService sOficios;

   @Autowired
   SubServicioService subServicioService;

    /*@GetMapping
    public List<?> obtenerOficios(TipoServicioModel tipoServicioModel, SubServiciosModel subServiciosModel){
        List<Categoria> listaOficios = tipoServicioRepository.findAll();
        subServiciosModel.getDescripcion();
    }*/
    /*public ArrayList<?> obtenerOficios(){
        return  ResponseEntity.ok(tipoServicioRepository.findAll());
    }*/

    @GetMapping()
    public ArrayList<TipoServicioModel> obtenerServicios(){
        return ResponseEntity.ok(sOficios.getAll()).getBody();
    }

    @GetMapping("/otro")
    public ArrayList<SubServiciosModel> obtenerSubservicios(){
        return ResponseEntity.ok(subServicioService.getAll()).getBody();
    }

}
