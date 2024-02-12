package com.app.gastrofy_backend.model.enums;

public enum Clasificacion {
    PRODUCCION ("PRODUCCION"),
    NO_PRODUCCION ("NO PRODUCCION"),
    NO_APLICA ("NO APLICA");

    private String descripcion;

    Clasificacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
