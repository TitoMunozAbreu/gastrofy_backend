package com.app.gastrofy_backend.model;

import com.app.gastrofy_backend.model.enums.Presentacion;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Entity
@Table
public class Producto {
    @Id
    @SequenceGenerator(
            name = "producto_sequence",
            sequenceName = "producto_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "producto_sequence"
    )
    private Integer idProducto;

    private String nombre;

    private Presentacion presentacion;

    private double costoReceta;

    private double cantidadResultante;

    private double costoFabricacion;

    private double costoFijosVariables;

    private double costoMaquinariaMantenimiento;

    private double costoManoObra;

    private double ganancia;

    private double precioVenta;

}
