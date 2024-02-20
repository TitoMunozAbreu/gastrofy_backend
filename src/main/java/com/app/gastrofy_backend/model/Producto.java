package com.app.gastrofy_backend.model;

import com.app.gastrofy_backend.model.enums.Presentacion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    private double precioVenta1;

    private double precioVenta2;

    private double precioVenta3;

    @ManyToOne
    @JoinColumn(
            name = "id_sistema_costo",
            referencedColumnName = "idSistemaCosto"

    )
    private SistemaCosto sistemaCosto;

    @ManyToMany
    @JoinTable(
            name = "producto_materia_prima",
            joinColumns = {
                    @JoinColumn(name = "id_producto", referencedColumnName = "idProducto")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_materia_prima", referencedColumnName = "idMateriaPrima")}
    )
    private List<MateriaPrima> materiaPrimas;

    @ManyToMany
    @JoinTable(
            name = "producto_semi_elaborado",
            joinColumns = {
                    @JoinColumn(name = "id_producto", referencedColumnName = "idProducto")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_semi_elaborado", referencedColumnName = "idSemiElaborado")}
    )
    private List<SemiElaborado> semiElaborados;


}
