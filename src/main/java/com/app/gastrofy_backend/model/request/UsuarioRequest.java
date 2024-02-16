package com.app.gastrofy_backend.model.request;

public record UsuarioRequest(String email,
                             String nombre,
                             String apellido,
                             String contrasena,
                             String movil,
                             String nombreEmpresa) {}
