package com.app.gastrofy_backend.controller;

import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.exceptions.ResourceNotFoundException;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.app.gastrofy_backend.controller.constante.EndpointUri.Api.Usuarios.USUARIOS;

@RequestMapping(value = USUARIOS)
public interface UsuarioApi {
    //registrar usuario
    @PostMapping
    ResponseEntity<HttpGlobalResponse<UsuarioResponse>> registrarUsuario(@RequestBody
                                                              @Valid UsuarioRequest usuarioDto) throws DuplicateResourceException;

    //listar usuarios
    @GetMapping
    ResponseEntity<HttpGlobalResponse<Page<UsuarioResponse>>> listarUsuarios(@RequestParam Optional<String> usuarioNombre,
                                                                             @PageableDefault Pageable pageable) throws ResourceNotFoundException;
}
