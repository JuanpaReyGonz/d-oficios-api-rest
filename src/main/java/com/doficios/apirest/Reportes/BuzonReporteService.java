package com.doficios.apirest.Reportes;

import com.doficios.apirest.Models.BuzonReportesModel;
import com.doficios.apirest.Repositories.BuzonReportesRepository;
import com.doficios.apirest.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class BuzonReporteService {
    @Autowired
    BuzonReportesRepository buzonReportesRepo;
    @Autowired
    UsuarioRepository usuarioRepo;

    public BuzonGeneralResponse insertBuzonGeneral(BuzonGeneralRequest request, String username) {
        int idUsuario = usuarioRepo.findByCorreo(username);
        //Obteniendo fecha hora actual
        ZoneId gmtMinus6 = ZoneId.of("GMT-6");
        ZonedDateTime nowInGmtMinus6 = ZonedDateTime.now(gmtMinus6);
        LocalDateTime fechaReporte = nowInGmtMinus6.toLocalDateTime();
        buzonReportesRepo.insertBuzonGeneral(idUsuario,fechaReporte, request.getContenido());

        return BuzonGeneralResponse.builder()
                .id_reporte(buzonReportesRepo.getLastInsertedId())
                .build();
    }
}
