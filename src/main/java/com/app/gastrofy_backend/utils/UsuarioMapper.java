package com.app.gastrofy_backend.utils;

import com.app.gastrofy_backend.model.Usuario;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.app.gastrofy_backend.utils.SistemaCostoMapper.mapSistemaEntidadToResponse;

/**
 * Clase para mapear Usuario
 */
@Component
@Slf4j
public class UsuarioMapper {

    public static Usuario mapUsuarioRequestToEntity(UsuarioRequest usuarioRequest){
        log.info("Mapping usuario");
        return Usuario.builder()
                .email(usuarioRequest.email().toLowerCase().trim())
                .nombre(usuarioRequest.nombre().toLowerCase().trim())
                .apellido(usuarioRequest.apellido().toLowerCase().trim())
                .contrasena(usuarioRequest.contrasena().trim())
                .movil(usuarioRequest.movil().trim())
                .imageUrl("https://cdn-icons-png.flaticon.com/512/149/149071.png")
                .rol("USER")
                .build();
    }

    public static UsuarioResponse mapUsuarioEntityToResponse(Usuario usuario){
        log.info("Mapping usuarioResponse ");
        return UsuarioResponse.builder()
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .movil(usuario.getMovil())
                .rol(usuario.getRol())
                .sistemaCosto(mapSistemaEntidadToResponse(usuario.getSistemaCosto()))
                .build();
    }
}
