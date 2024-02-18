package com.app.gastrofy_backend.model.response;

import lombok.*;

import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor(access = PUBLIC)
@AllArgsConstructor
@Builder
public class UsuarioResponse {
    private String email;
    private String nombre;
    private String apellido;
    private String movil;
    private String rol;
    private String nombreEmpresa;
}
