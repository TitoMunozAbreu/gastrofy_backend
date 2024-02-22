package com.app.gastrofy_backend.services.impl;

import com.app.gastrofy_backend.model.MateriaPrima;
import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.request.MateriaPrimaRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.MateriaPrimaResponse;
import com.app.gastrofy_backend.repositories.MateriaPrimaRepository;
import com.app.gastrofy_backend.services.MateriaPrimaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.gastrofy_backend.utils.MateriaPrimaMapper.mapListEntidadToMateriaPrimaResponse;
import static com.app.gastrofy_backend.utils.MateriaPrimaMapper.mapListMateriaPrimaRequestToEntidad;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;

@Service
@Transactional(rollbackFor = {ArithmeticException.class})
@Slf4j
public class MateriaPrimaServiceImpl implements MateriaPrimaService {

    private final MateriaPrimaRepository materiaPrimaRepository;

    public MateriaPrimaServiceImpl(MateriaPrimaRepository materiaPrimaRepository) {
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<List<MateriaPrimaResponse>>> registrarMateriaPrima(List<MateriaPrimaRequest> materiaPrimaRequest,
                                                                                                SistemaCosto sistemaCosto) {
        log.info("Registrar materia prima");
        //mapear listado de materia prima a Entidades
        List<MateriaPrima> materiaPrimas = mapListMateriaPrimaRequestToEntidad(materiaPrimaRequest, sistemaCosto);
        //persistir listado en la BBDD
        materiaPrimaRepository.saveAll(materiaPrimas);

        return ResponseEntity.ok()
                .body(HttpGlobalResponse.<List<MateriaPrimaResponse>>builder()
                        .timeStamp(now())
                        .statusCode(CREATED.value())
                        .status(CREATED)
                        .message("Materias primas  registrada con exito")
                        .data(of("materias primas", mapListEntidadToMateriaPrimaResponse(materiaPrimas)))
                        .build());
    }

}
