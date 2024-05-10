package com.doficios.apirest.Repositories;

import com.doficios.apirest.Models.BuzonReportesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface BuzonReportesRepository extends JpaRepository<BuzonReportesModel,Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO buzon_general (id_usuario, fecha, contenido) VALUES (:id_usuario, :fecha, :contenido)",
            nativeQuery = true)
    void insertBuzonGeneral(int id_usuario, LocalDateTime fecha, String contenido);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    int getLastInsertedId();
}
