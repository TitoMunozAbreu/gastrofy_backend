package com.app.gastrofy_backend.controller.impl;

import com.app.gastrofy_backend.controller.UsuarioApi;
import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.exceptions.ResourceNotFoundException;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import com.app.gastrofy_backend.services.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UsuarioController implements UsuarioApi {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<UsuarioResponse>> registrarUsuario(UsuarioRequest usuarioRequest) throws DuplicateResourceException {
        return usuarioService.registrarUsuario(usuarioRequest);
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<Page<UsuarioResponse>>> listarUsuarios(Optional<String> usuarioNombre,
                                                                                    Pageable pageable) throws ResourceNotFoundException {
        return usuarioService.listarUsuarios(usuarioNombre.orElse(""),
                                             pageable);
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<UsuarioResponse>> obtenerUsuarioPorID(Integer usuarioID) throws ResourceNotFoundException {
        return usuarioService.obtenerUsuarioPorID(usuarioID);
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<UsuarioResponse>> actualizarUsuarioPorID(Integer usuarioID,
                                                                                      UsuarioRequest usuarioRequest) throws ResourceNotFoundException,
                                                                                                                            DuplicateResourceException {
        return usuarioService.actualizarUsuarioPorID(usuarioID, usuarioRequest);
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<?>> eliminarUsuarioPorID(Integer usuarioID) throws ResourceNotFoundException {
        return usuarioService.eliminarUsuarioPorID(usuarioID);
    }

}
