package com.doficios.apirest.Oficios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubServiciosRepository extends JpaRepository<SubServiciosModel,Integer> {
}
