package com.app.gastrofy_backend.model.enums;

public enum Categoria {
    INSUMOS ("INSUMOS"),
    MATERIA_PRIMA ("MATERIA PRIMA"),
    BEBIDAS ("BEBIDAS"),
    LIMPIEZA ("LIMPIEZA");

    private String descripcion;

    Categoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
