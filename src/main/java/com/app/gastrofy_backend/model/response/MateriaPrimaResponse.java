package com.app.gastrofy_backend.model.response;

import com.app.gastrofy_backend.model.enums.Categoria;
import com.app.gastrofy_backend.model.enums.Presentacion;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
public class MateriaPrimaResponse {
    private String codigoProducto;
    private String nombre;
    private double precioCompra;
    private double cantidad;
    private Presentacion presentacion;
    private double costoUnitarioKg;
    private double costoUnitarioGr;
    private double costoUnitarioLb;
    private double costoUnitarioOz;
    private String proveedor;
    private Categoria categoria;
}
