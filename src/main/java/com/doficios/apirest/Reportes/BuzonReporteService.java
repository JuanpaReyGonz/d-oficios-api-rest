package com.doficios.apirest.Reportes;

import com.doficios.apirest.Models.BuzonReportesModel;
import com.doficios.apirest.Repositories.BuzonReportesRepository;
import com.doficios.apirest.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
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

    @Transactional(readOnly = true)
    public List<BuzonGeneralGetAllResponse> obtenerReportes(String username) {
        //List<BuzonGeneralGetAllResponse> buzonReportesAll = buzonReportesRepo.findAll();
        List<BuzonReportesModel> buzonReportesModel = buzonReportesRepo.findAll();
        List<BuzonGeneralGetAllResponse> buzonGeneralGetAll = new ArrayList<>();
        for (BuzonReportesModel getAll : buzonReportesModel){
            //Cambiar formato de Fecha a String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //Convertir LocalDateTime a String
            String fecha = getAll.getFecha().format(formatter);
            BuzonGeneralGetAllResponse buzonGeneralGetAllResponse = BuzonGeneralGetAllResponse.builder()
                    .id_reporte(getAll.getId_reporte())
                    .id_usuario(Math.toIntExact(getAll.getUsuarioModel().getId()))
                    .correo(getAll.getUsuarioModel().getCorreo())
                    .fecha(fecha)
                    .contenido(getAll.getContenido())
                    .build();
            buzonGeneralGetAll.add(buzonGeneralGetAllResponse);
        }
        return buzonGeneralGetAll;
    }

}
