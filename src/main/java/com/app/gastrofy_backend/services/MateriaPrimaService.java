package com.app.gastrofy_backend.services;

import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.request.MateriaPrimaRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.MateriaPrimaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MateriaPrimaService {
    ResponseEntity<HttpGlobalResponse<List<MateriaPrimaResponse>>> registrarMateriaPrima(List<MateriaPrimaRequest> materiaPrimaRequest,
                                                                                         SistemaCosto sistemaCosto);
}
