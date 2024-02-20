package com.app.gastrofy_backend.model.enums;

public enum Clasificacion {
    PRODUCCION ("PRODUCCION"),
    ADMINISTRATIVO ("ADMINISTRATIVO");
    private String descripcion;

    Clasificacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
