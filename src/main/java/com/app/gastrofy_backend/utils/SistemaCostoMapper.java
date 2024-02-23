package com.app.gastrofy_backend.utils;

import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.response.SistemaCostoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SistemaCostoMapper {

    public static SistemaCostoResponse mapSistemaEntidadToResponse(SistemaCosto sistemaCosto) {
        log.info("Mapping sistemaCostoResponse ");
        return SistemaCostoResponse.builder()
                .idSistemaCosto(sistemaCosto.getIdSistemaCosto())
                .nombreEmpresa(sistemaCosto.getNombreEmpresa())
                .globalPresentacion(sistemaCosto.getGlobalPresentacion())
                .build();
    }
}
