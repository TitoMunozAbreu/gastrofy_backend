package com.app.gastrofy_backend.controller.impl;


import com.app.gastrofy_backend.controller.MateriaPrimaApi;
import com.app.gastrofy_backend.exceptions.ResourceNotFoundException;
import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.request.MateriaPrimaRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.MateriaPrimaResponse;
import com.app.gastrofy_backend.services.MateriaPrimaService;
import com.app.gastrofy_backend.services.SistemaCostoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MateriaPrimaController implements MateriaPrimaApi {

    private final MateriaPrimaService materiaPrimaService;
    private final SistemaCostoService sistemaCostoService;

    public MateriaPrimaController(MateriaPrimaService materiaPrimaService, SistemaCostoService sistemaCostoService) {
        this.materiaPrimaService = materiaPrimaService;
        this.sistemaCostoService = sistemaCostoService;
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<List<MateriaPrimaResponse>>> registrarMateriaPrima(Integer sistemaID,
                                                                                                List<MateriaPrimaRequest> materiaPrimaRequest) throws ResourceNotFoundException {
        //comprobar si sistema costo nombre existe
        SistemaCosto sistemaCosto = sistemaCostoService.obtenerSistemaCostoPorID(sistemaID);
        return materiaPrimaService.registrarMateriaPrima(materiaPrimaRequest,sistemaCosto);
    }
}
