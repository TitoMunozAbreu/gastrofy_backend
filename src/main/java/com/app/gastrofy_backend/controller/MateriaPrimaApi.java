package com.app.gastrofy_backend.controller;

import com.app.gastrofy_backend.exceptions.ResourceNotFoundException;
import com.app.gastrofy_backend.model.request.MateriaPrimaRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.MateriaPrimaResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.app.gastrofy_backend.controller.constante.EndpointUri.Api.SistemaCostos.SISTEMA_COSTOS;

@RequestMapping(value = SISTEMA_COSTOS)
public interface MateriaPrimaApi {
    @PostMapping("/{scID}/materia-prima")
    ResponseEntity<HttpGlobalResponse<List<MateriaPrimaResponse>>> registrarMateriaPrima(@PathVariable("scID") Integer sistemaID,
                                                                                                @Valid @RequestBody
                                                                                                List<MateriaPrimaRequest> materiaPrimaRequest) throws ResourceNotFoundException;
}
