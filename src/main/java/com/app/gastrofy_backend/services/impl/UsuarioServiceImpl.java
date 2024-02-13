package com.app.gastrofy_backend.services.impl;

import com.app.gastrofy_backend.model.Usuario;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.HttpGlobalResponse;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import com.app.gastrofy_backend.repositories.UsuarioRepository;
import com.app.gastrofy_backend.services.SistemaCostoService;
import com.app.gastrofy_backend.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.app.gastrofy_backend.utils.UsuarioMapper.mapUsuarioRequestToEntity;

@Service
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final SistemaCostoService sistemaCostoService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, SistemaCostoService sistemaCostoService) {
        this.usuarioRepository = usuarioRepository;
        this.sistemaCostoService = sistemaCostoService;
    }

    @Override
    public ResponseEntity<HttpGlobalResponse<UsuarioResponse>> registrarUsuario(UsuarioRequest usuarioDto) {
        log.info("Registrando usuario: {}", usuarioDto.nombre());
        //mapear usuarioRequest a usuario
        Usuario usuario = mapUsuarioRequestToEntity(usuarioDto);
        //crear y asociar sistema de costo al usuario
        sistemaCostoService.registrarSistemaCosto(usuarioDto.nombreEmpresa(), usuario);
        //persistir usuario
        usuarioRepository.save(usuario);
        //perisistir sistema de costo

        return null;
    }
}
