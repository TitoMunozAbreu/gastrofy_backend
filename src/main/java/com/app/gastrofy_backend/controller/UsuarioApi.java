package com.app.gastrofy_backend.controller;

import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.app.gastrofy_backend.controller.constante.EndpointUri.Api.Usuarios.USUARIOS;

@RequestMapping(value = USUARIOS)
public interface UsuarioApi {
    //registrar usuario
    @PostMapping
    ResponseEntity<HttpGlobalResponse<UsuarioResponse>> registrarUsuario(@RequestBody
                                                              @Valid UsuarioRequest usuarioDto);
}
