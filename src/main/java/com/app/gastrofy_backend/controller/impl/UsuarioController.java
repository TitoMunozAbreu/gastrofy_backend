package com.app.gastrofy_backend.controller.impl;

import com.app.gastrofy_backend.controller.UsuarioApi;
import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import com.app.gastrofy_backend.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
