package com.app.gastrofy_backend.model.enums;

public enum TipoCosto {
    COSTOS_INDIRECTOS ("GASTOS ADMINISTRATIVOS / COSTOS INDIRECTOS"),
    COSTOS_VARIABLES ("COSTOS VARIABLES"),
    NOMINA ("NOMINA"),
    MAQUINARIA_EQUIPOS ("MAQUINARIA Y EQUIPOS"),
    MANTENIMIENTO ("MANTENIMIENTO DE EQUIPOS"),
    ELECTRICIDAD ("ELECTRICIDAD"),
    GAS ("GAS"),
    ALQUILER ("ALQUILER"),
    MARKETING ("MARKETING");

    private String descripcion;

    TipoCosto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
