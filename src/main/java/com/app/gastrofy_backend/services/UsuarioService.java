package com.app.gastrofy_backend.services;

import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.exceptions.ResourceNotFoundException;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<HttpGlobalResponse<UsuarioResponse>> registrarUsuario(UsuarioRequest usuarioRequest) throws DuplicateResourceException;

    ResponseEntity<HttpGlobalResponse<Page<UsuarioResponse>>> listarUsuarios(String usuarioName, Pageable pageable) throws ResourceNotFoundException;

    ResponseEntity<HttpGlobalResponse<UsuarioResponse>> obtenerUsuarioPorID(Integer usuarioID) throws ResourceNotFoundException;
}
