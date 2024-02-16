package com.app.gastrofy_backend.utils;

import com.app.gastrofy_backend.model.Usuario;
import com.app.gastrofy_backend.model.response.UsuarioResponse;
import com.app.gastrofy_backend.model.request.UsuarioRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Clase para mapear Usuario
 */
@Component
@Slf4j
public class UsuarioMapper {

    public static Usuario mapUsuarioRequestToEntity(UsuarioRequest usuarioRequest){
        log.info("Mapping usuario");
        return Usuario.builder()
                .email(usuarioRequest.email())
                .nombre(usuarioRequest.nombre())
                .apellido(usuarioRequest.apellido())
                .contrasena(usuarioRequest.contrasena())
                .movil(usuarioRequest.movil())
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
                .build();
    }
}
