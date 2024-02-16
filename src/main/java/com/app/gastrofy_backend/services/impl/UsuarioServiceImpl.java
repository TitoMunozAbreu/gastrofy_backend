package com.app.gastrofy_backend.services.impl;

import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.model.Usuario;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import com.app.gastrofy_backend.repositories.UsuarioRepository;
import com.app.gastrofy_backend.services.SistemaCostoService;
import com.app.gastrofy_backend.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

import static com.app.gastrofy_backend.utils.UsuarioMapper.mapUsuarioEntityToResponse;
import static com.app.gastrofy_backend.utils.UsuarioMapper.mapUsuarioRequestToEntity;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Service
@Transactional(rollbackFor = {DuplicateResourceException.class})
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final SistemaCostoService sistemaCostoService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, SistemaCostoService sistemaCostoService) {
        this.usuarioRepository = usuarioRepository;
        this.sistemaCostoService = sistemaCostoService;
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<UsuarioResponse>> registrarUsuario(UsuarioRequest usuarioRequest) throws DuplicateResourceException {
        log.info("Comprobar si se encuentra registrado email {}", usuarioRequest.email());
        Boolean existePorEmail = usuarioRepository.existsByEmail(usuarioRequest.email());
        //comprobar si el email se encuentra registrado
        if(existePorEmail){
            throw new DuplicateResourceException("Email '%s' se encuentra registrado"
                    .formatted(usuarioRequest.email()));
        }
        log.info("Registrando usuario: {}", usuarioRequest.nombre());
        //mapear usuarioRequest a usuario
        Usuario usuario = mapUsuarioRequestToEntity(usuarioRequest);
        //persistir usuario
        usuarioRepository.save(usuario);
        //crear y asociar sistema de costo al usuario
        sistemaCostoService.registrarSistemaCosto(usuarioRequest.nombreEmpresa(), usuario);

        return ResponseEntity.created(getURI(usuario.getIdUsuario()))
                .body(HttpGlobalResponse.<UsuarioResponse>builder()
                        .timeStamp(now())
                        .statusCode(OK.value())
                        .status(CREATED)
                        .message("Usuario '%s' registrado con exito".formatted(usuario.getNombre()))
                        .data(of("usuario", mapUsuarioEntityToResponse(usuario)))
                        .build());
    }

    // Metodo para definir la URI de la ubicacion del objeto creado
    private URI getURI(Integer id) {
        return fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
