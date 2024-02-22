package com.app.gastrofy_backend.services.impl;

import com.app.gastrofy_backend.exceptions.DuplicateResourceException;
import com.app.gastrofy_backend.exceptions.ResourceNotFoundException;
import com.app.gastrofy_backend.model.SistemaCosto;
import com.app.gastrofy_backend.model.Usuario;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import com.app.gastrofy_backend.repositories.UsuarioRepository;
import com.app.gastrofy_backend.services.SistemaCostoService;
import com.app.gastrofy_backend.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Map;

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
        log.info("Registrar usuario: {}", usuarioRequest.nombre());
        //mapear usuarioRequest a usuario
        Usuario usuario = mapUsuarioRequestToEntity(usuarioRequest);
        //crear y asociar sistema de costo al usuario
        SistemaCosto sistemaCosto = sistemaCostoService.registrarSistemaCosto(usuarioRequest.nombreEmpresa(), usuario);
        usuario.setSistemaCosto(sistemaCosto);
        //persistir usuario
        usuarioRepository.save(usuario);

        return ResponseEntity.created(getURI(usuario.getIdUsuario()))
                .body(HttpGlobalResponse.<UsuarioResponse>builder()
                        .timeStamp(now())
                        .statusCode(OK.value())
                        .status(CREATED)
                        .message("Usuario '%s' registrado con exito".formatted(usuario.getNombre()))
                        .data(of("usuario", mapUsuarioEntityToResponse(usuario)))
                        .build());
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<Page<UsuarioResponse>>> listarUsuarios(String usuarioName,
                                                                                    Pageable pageable) throws ResourceNotFoundException {
        log.info("Obtener usuarios por nombre '{}', pageable '{}'", usuarioName, pageable);
        //obtener lista de usuarios
        Page<UsuarioResponse> usuarioResponsePage = usuarioRepository.findByNombreContaining(usuarioName, pageable)
                .map(usuario -> mapUsuarioEntityToResponse(usuario));
        //comprobar que la lista no esta vacia
        if(usuarioResponsePage.getContent().isEmpty()) {
            throw new ResourceNotFoundException("Usuarios no registrados");
        }
        return ResponseEntity.ok().body(HttpGlobalResponse.<Page<UsuarioResponse>>builder()
                        .timeStamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Usuarios encontrado")
                        .data(Map.of("usuarios", usuarioResponsePage))
                        .build());
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<UsuarioResponse>> obtenerUsuarioPorID(Integer usuarioID) throws ResourceNotFoundException {
        log.info("Obtener usuario por ID '{}'", usuarioID);
        UsuarioResponse usuarioResponse = usuarioRepository.findById(usuarioID)
                .map(usuario -> mapUsuarioEntityToResponse(usuario))
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID '%s' no existe"
                        .formatted(usuarioID)));
        return ResponseEntity.ok().body(HttpGlobalResponse.<UsuarioResponse>builder()
                        .timeStamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Usuario '%s' encontrado".formatted(usuarioResponse.getNombre()))
                        .data(of("usuario", usuarioResponse))
                        .build());
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<UsuarioResponse>> actualizarUsuarioPorID(Integer usuarioID,
                                                                                      UsuarioRequest usuarioRequest) throws ResourceNotFoundException, DuplicateResourceException {
        log.info("Actualizar usuario con ID '{}'", usuarioID);
        //comprobar si el usuario se encuentra registrado
        Usuario usuarioEncontrado = usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID '%s' no se encuentra registrado"
                        .formatted(usuarioID)));
        //comprobar si el email a registrar sea diferente y no se encuntre registrado
        if(!usuarioEncontrado.getEmail().equals(usuarioRequest.email()) && !existeUsuarioPorEmail(usuarioRequest.email())){
            usuarioEncontrado.setEmail(usuarioRequest.email());
        }
        //actualizar datos al usuarioEncontrado
        usuarioEncontrado.setNombre(usuarioRequest.nombre());
        usuarioEncontrado.setApellido(usuarioRequest.apellido());
        usuarioEncontrado.setContrasena(usuarioRequest.contrasena());
        usuarioEncontrado.setMovil(usuarioRequest.movil());
        usuarioEncontrado.getSistemaCosto().setNombreEmpresa(usuarioRequest.nombreEmpresa());
        //persistir usuario con los nuevos datos
        usuarioRepository.save(usuarioEncontrado);

        return ResponseEntity.ok().body(HttpGlobalResponse.<UsuarioResponse>builder()
                .timeStamp(now())
                .statusCode(OK.value())
                .status(OK)
                .message("Usuario '%s' actualizado".formatted(usuarioEncontrado.getNombre()))
                .data(of("usuario", mapUsuarioEntityToResponse(usuarioEncontrado)))
                .build());
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<?>> eliminarUsuarioPorID(Integer usuarioID) throws ResourceNotFoundException {
        log.info("Eliminar usuario con ID '{}'", usuarioID);
        //comprobar si el usuario se encuentra registrado
        Usuario usuarioEncontrado = usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID '%s' no se encuentra registrado"
                        .formatted(usuarioID)));
        //eliminar sistema de costo del usuario
        sistemaCostoService.eliminarSistemaCostoPorNombre(usuarioEncontrado.getSistemaCosto().getNombreEmpresa());
        //eliminar usuario de la BBDD
        usuarioRepository.delete(usuarioEncontrado);

        return ResponseEntity.ok().body(HttpGlobalResponse.builder()
                .timeStamp(now())
                .statusCode(OK.value())
                .status(OK)
                .message("Usuario '%s' Eliminado".formatted(usuarioEncontrado.getNombre()))
                .build());
    }

    public boolean existeUsuarioPorEmail(String email) throws DuplicateResourceException {
        log.info("Comprobar si se encuentra registrado email {}", email);
        Boolean existePorEmail = usuarioRepository.existsByEmail(email);
        //comprobar si el email se encuentra registrado
        if(existePorEmail){
            throw new DuplicateResourceException("Email '%s' se encuentra registrado"
                    .formatted(email));
        }
        return existePorEmail;
    }

    // Metodo para definir la URI de la ubicacion del objeto creado
    private URI getURI(Integer id) {
        return fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
