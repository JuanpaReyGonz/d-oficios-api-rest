package com.doficios.apirest.Oficios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class SubServicioService {
    @Autowired
    SubServiciosRepository subServiciosRepo;

    @Transactional(readOnly = true)
    public ArrayList<SubServiciosModel> getAll() {
        return (ArrayList<SubServiciosModel>) subServiciosRepo.findAll();
    }
}
